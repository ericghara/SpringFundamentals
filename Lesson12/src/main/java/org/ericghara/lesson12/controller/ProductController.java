package org.ericghara.lesson12.controller;

import org.ericghara.lesson12.enteties.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ericghara.lesson12.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/sort")
    public Iterable<Product> getAllSortedDescendingById() {
        return productService.getAllSortedDescendingById();
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product p) {
        productService.addProduct(p);
    }

    @GetMapping("/page/{page}")
    public List<Product> getProductsByPage(@PathVariable int page) {
        return productService.getProductsByPage(page);
    }

    @GetMapping("/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getByName(name);
    }

    @GetMapping("/name/{name}/{page}")
    public List<Product> getProductsByName(@PathVariable String name,
                                           @PathVariable int page) {
        return productService.getByName(name, page);
    }

}
