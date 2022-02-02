package org.ericghara.lesson12a.service;

import org.ericghara.lesson12a.model.Product;
import org.ericghara.lesson12a.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAllByName(String name) {
        return productRepository.findProductsByName(name);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }



}
