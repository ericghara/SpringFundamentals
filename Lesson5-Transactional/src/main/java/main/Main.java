package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;

public class Main {

    public static void main(String[] args) {
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class) ) {
            ProductService p = c.getBean(ProductService.class);
            System.out.println("This is supposed to rollback a failed transaction");
            p.addOneProduct();
        }
    }
}
