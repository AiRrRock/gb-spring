package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByPriceLessThan(Integer maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findByPriceGreaterThan(Integer minPrice) {
        return productRepository.findAllByPriceGreaterThan(minPrice);
    }


    public List<Product> findByPriceInInterval(Integer minPrice, Integer maxPrice) {
        return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
    }


    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
