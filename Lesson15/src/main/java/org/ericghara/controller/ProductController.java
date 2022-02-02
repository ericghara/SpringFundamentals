package org.ericghara.controller;

import org.ericghara.entities.Product;
import org.ericghara.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{name}")
    @ResponseBody
    public Product getProduct(@PathVariable String name, HttpServletResponse res) {
        Optional<Product> p = productService.getProductByName(name);
        if (p.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return p.orElse(null);
    }
}
