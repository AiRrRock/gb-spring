package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk", 80),
                new Product(2L, "Corn", 90),
                new Product(3L, "Carp", 100),
                new Product(4L, "Bread", 90),
                new Product(4L, "Bread", 90)
        ));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findById(Long id) {
        return products.stream().filter(s -> s.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
    }
}
