package org.ericghara.Lesson10.services;

import org.ericghara.Lesson10.repositories.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        clearProducts(); // clear out the repository
    }


    public void addProduct(String name, double price ) {
        productRepository.addProduct(name, price);
    }

    public void clearProducts() {
        productRepository.truncateProduct();
    }
}
