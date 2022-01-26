package org.ericghara.Lesson10.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public void addProduct(String name, double price) {
        String sql = "INSERT INTO product VALUES(DEFAULT, ?, ?)";
        jdbcTemplate.update(sql, name, price);
    }

    public void truncateProduct() {
        String sql = "TRUNCATE product";
        jdbcTemplate.update(sql);
    }
}
