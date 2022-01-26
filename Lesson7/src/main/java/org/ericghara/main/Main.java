package org.ericghara.main;

import org.ericghara.config.ProjectConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.ericghara.services.ProductService;

public class Main {

    public static void main(String[] args) {
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class) ) {
            ProductService p = c.getBean(ProductService.class);
            p.clearAllData();
            p.addTenProducts();
        }
    }
}
