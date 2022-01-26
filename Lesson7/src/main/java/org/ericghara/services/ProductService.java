package org.ericghara.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.ericghara.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    /*
    READ_COMMITTED (default)
    READ_UNCOMMITTED
    REPEATABLE
    SERIALIZABLE

    PROBLEMS:
        -dirty reads - reads an uncommitted concurrent transaction
        -repeatable reads - multiple reads of same field within a transaction
                            are different due to a concurrent transaction
        -phantom reads - when referring to multiple records, the number of total
                         records changes.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addTenProducts() {
        for (int i = 1; i < 10; i++) {
            productRepository.addProduct(String.format("BMW %d31i", i), i*15_000D);
        }
    }

    @Transactional
    public void clearAllData() {
        productRepository.truncate();
    }


}
