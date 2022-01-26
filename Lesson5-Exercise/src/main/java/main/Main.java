package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.ProductService;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        try (var c = new AnnotationConfigApplicationContext(ProjectConfig.class) ) {
            ProductService p = c.getBean(ProductService.class);
            String[][] products1 = {
                    {"071662040505", "Crayola 50ct Colored Pencils", "7.69"},
                    { "071662034244", "Crayola Erasable Colored Pencils 24ct", "5.99"}, // purposely invalid upc;
                    { "071662040246", "Crayola 24ct Pre-Sharpened Colored Pencils", "2.99"},

            };
            String[][] products2 = {
                    {"071662069247", "Crayola 24ct Ultra Clean Washable Crayons", "3.39"},
                    { "071662000646", "Crayola 64ct Classic Crayons with Sharpener", "3.19"},
                    { "071662032808", "Crayola 8ct Washable Large Crayons", "2.19"}, // purposely invalid upc

            };
            p.clearAll(); // **Truncates the table
            p.addValidProducts(products1);  // rolls back invalid products
            try {
                p.addValidProductsAtomic(products2); // rolls back all products
            } catch (IllegalArgumentException e) {
                StringBuilder builder = new StringBuilder();
                Arrays.asList(products2).forEach( (l) -> builder.append(' ').append(l[0]) );
                System.out.printf("A UPC error with one or more products caused none of them to be added:%s.%n", builder);
            }
        }
    }
}
