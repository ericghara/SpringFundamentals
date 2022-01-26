package org.ericghara.Lesson10.controllers;

import org.ericghara.Lesson10.services.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/add/{name}", params = {"price"} )
    public void addProduct(@PathVariable String name, double price) {
        productService.addProduct(name, price);
    }
}
