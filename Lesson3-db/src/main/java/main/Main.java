package main;

import config.ProjectConfig;
import model.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.ProductRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class) ) {
            ProductRepository productRepository = c.getBean(ProductRepository.class);

            Product p = new Product();
            p.setName("Chocolate");
            p.setPrice(5.99);

            productRepository.addProduct(p);
            List<Product> products = productRepository.getProducts();
            products.forEach(System.out::println);
        }
    }

}
