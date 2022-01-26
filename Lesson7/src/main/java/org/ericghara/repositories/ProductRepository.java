package org.ericghara.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void addProduct(String name, double price) {
        String sql = "INSERT INTO product VALUES(DEFAULT, ?, ?)";
        jdbcTemplate.update(sql, name, price);
    }

    @Transactional
    public void truncate() {
        String sql = "TRUNCATE product";
        jdbcTemplate.execute(sql);
    }

}
