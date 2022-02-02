package org.ericghara.repositories;

import org.ericghara.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductsByName(String name);

    List<Product> findProductsByName(String name, Sort sort);

    List<Product> findProductsByName(String name, Pageable p);

    @Query(value="SELECT p.name FROM Product p")
    List<String> allProductNames();

    @Query(value="SELECT p.id FROM Product p")
    List<Integer> allProductIds();

    @Query(value="SELECT p.price FROM Product p")
    List<Double> allProductPrices();
}