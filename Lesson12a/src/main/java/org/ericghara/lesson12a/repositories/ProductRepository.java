package org.ericghara.lesson12a.repositories;

import org.ericghara.lesson12a.model.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    // can't use paging and sorting b/c using jdbc
    // also can't auto-inject queries from method names

    @Query("SELECT * FROM product WHERE name=:name")
    List<Product> findProductsByName(String name);

}
