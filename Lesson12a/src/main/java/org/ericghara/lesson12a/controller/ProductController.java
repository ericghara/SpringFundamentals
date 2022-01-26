package org.ericghara.lesson12a.controller;

import org.ericghara.lesson12a.model.Product;
import org.ericghara.lesson12a.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/name/{name}")
    public Iterable<Product> findAllByName(@PathVariable String name) {
        return productService.findAllByName(name);
    }

    @GetMapping("/all")
    public Iterable<Product> findAll() {
        return productService.findAll();
    }
}
