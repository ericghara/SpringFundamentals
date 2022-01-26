package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import repositories.ProductRepository;

import java.math.BigInteger;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public void addValidProducts(String [][] prods) throws UnexpectedRollbackException {
        for (String[] line : prods) {
            if (line.length != 3) {
                throw new IllegalArgumentException("Caught an invalid input");
            }
            BigInteger upc = new BigInteger(line[0]);
            String name = line[1];
            double price = Double.parseDouble(line[2]);
            try {
                this.addOneProduct(upc, name, price);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage() );
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addValidProductsAtomic(String [][] prods) throws UnexpectedRollbackException {
        for (String[] line : prods) {
            if (line.length != 3) {
                throw new IllegalArgumentException("Caught an invalid input");
            }
            BigInteger upc = new BigInteger(line[0]);
            String name = line[1];
            double price = Double.parseDouble(line[2]);
            try {
                this.addOneProduct(upc, name, price);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("No products were added due to an invalid input");
            }
        }
    }

    public void clearAll() {
        productRepository.clearAllProducts();
    }

    @Transactional// PlatformTransactionManager will only roll back on RuntimeExceptions (by default...)
    //@Transactional (noRollbackFor = RuntimeException.class, rollbackFor = Exception.class)  // rolls back checked exceptions but not runtime
    private void addOneProduct(BigInteger upc, String name, double price) {
        try {
            productRepository.addProduct(upc, name, price);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to add upc " + upc + " to the database.");
        }
    }

}
