package org.ericghara.lesson11.controllers;

import org.ericghara.lesson11.enteties.Product;
import org.ericghara.lesson11.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product p) {
        productService.addProduct(p);
    }

    @GetMapping("/get/{name}")
    public Product getProduct(@PathVariable String name) {
        return productService.getProduct(name);
    }
}
