package org.ericghara.services;

import org.ericghara.entities.Product;
import org.ericghara.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product p) {
        productRepository.save(p);
    }

    public void deleteAll() {
        productRepository.deleteAllInBatch();
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findProductsByName(name).stream().findAny();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public int numProductsWithPriceBetween(double low, double hi) {
        return (int) productRepository.allProductPrices()
                                      .stream()
                                      .filter( (price) -> price > low && price < hi )
                                      .count();
    }

    private <T> List<T> getAllBy(Function<Product,T> getter) {
        return getAllProducts().stream()
                               .map(getter)
                               .toList();
     }
}
