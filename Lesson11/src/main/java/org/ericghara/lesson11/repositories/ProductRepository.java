package org.ericghara.lesson11.repositories;

import org.ericghara.lesson11.enteties.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // spring data auto generates method
    //@Query("") // Provide any querry, and spring data will ignore method name
    Product findProductByName(String name);

}
