package repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Transactional(propagation = Propagation.REQUIRES_NEW)  // If uncommented Atomic will only roll back invalid transaction
    @Transactional
    public void addProduct(BigInteger upc, String name, double price) {
        String sql = "INSERT INTO product_upc VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,upc, name, price);
        mustBeValidUPC(upc);
    }

    public void clearAllProducts() {
        String sql = "TRUNCATE product_upc";
        jdbcTemplate.execute(sql);
    }

    /**
     * Supports EAN-13 and UPC-A
     */
    private void mustBeValidUPC(BigInteger upc) {
        // Note this is a dubious design choice to validate after adding...but
        // it serves as an example...
        BigInteger allDigits = upc;
        int even = 0;
        int odd = 0;
        int checkDigit = -1;
        for (int i = 1; i <= 13; i++) {
            BigInteger d = allDigits.mod(BigInteger.TEN);
            allDigits = allDigits.divide(BigInteger.TEN);
            if (i == 1) {
                checkDigit = d.intValue();
            }
            else if (1 == (i&1) ) {
                odd +=d.intValue();
            }
            else {
                even +=d.intValue();
            }
        }
        if (!allDigits.equals(BigInteger.ZERO) || upc.compareTo(BigInteger.ZERO) < 0) { // Filters negative UPCs and UPCs greater than 13 digits, allows 12 digit UPCs
            throw new IllegalArgumentException("Received an invalid upc: " + upc);
        }
        int ExpectedCheckDigit = 10 - ( (even * 3 + odd) % 10);
        if (ExpectedCheckDigit != checkDigit) {
            throw new IllegalArgumentException("Caught an invalid UPC: " + upc);
        }
    }

}
