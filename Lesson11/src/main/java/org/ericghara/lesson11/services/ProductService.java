package org.ericghara.lesson11.services;

import org.ericghara.lesson11.enteties.Product;
import org.ericghara.lesson11.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        clearProducts();
    }

    public void addProduct(Product p) {
        productRepository.save(p);
    }

    // Must be a unique name and must exist
    public Product getProduct(String name) {
        return productRepository.findProductByName(name);
    }

    public void clearProducts() {
        productRepository.deleteAll();
    }
}
