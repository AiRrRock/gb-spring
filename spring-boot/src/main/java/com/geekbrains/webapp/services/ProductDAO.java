package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDAO {
    private ProductRepository productRepository;

    @Autowired
    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public Product findByIdWithOrders(Long id) {
        return productRepository.findByIdWithOrders(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.saveOrUpdate(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
