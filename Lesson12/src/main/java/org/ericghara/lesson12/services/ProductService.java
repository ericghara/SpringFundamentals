package org.ericghara.lesson12.services;

import org.ericghara.lesson12.enteties.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ericghara.lesson12.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAllSortedDescendingById() {
        return productRepository.findAll(Sort.by("id").descending());
    }

    public List<Product> getProductsByPage(int page) {
        Sort s = Sort.by("id").descending();
        return productRepository.findAll(PageRequest.of(page, 3, s) ).getContent();
    }

    public void addProduct(Product p) {
        productRepository.save(p);
    }

    public List<Product> getByName(String name) {
        return productRepository.findProductsByName(name, Sort.by("id").descending() );
    }

    public List<Product> getByName(String name, int page) {
        Sort s = Sort.by("id").descending();
        Pageable p = PageRequest.of(page, 3, s);
        return productRepository.findProductsByName(name,  p);
    }
}
