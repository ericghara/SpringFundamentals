package org.ericghara.lesson12.repositories;

import org.ericghara.lesson12.enteties.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductsByName(String name);

    List<Product> findProductsByName(String name, Sort sort);

    List<Product> findProductsByName(String name, Pageable p);

}
