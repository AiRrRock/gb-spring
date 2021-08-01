package ru.gb.lesson2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Cart {
    private ProductRepository repository;
    private Map<Long, List<Product>> carts;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public void addProductToCart(long userId, long id) {
        Product product = repository.findById(id);
        if (product != null) {
            List<Product> products = carts.get(userId);
            if (products == null) {
                products = new ArrayList<>();
            }
            products.add(product);
            carts.put(userId, products);
        }
    }

    public void removeProduct(long userId, long id) {
        List<Product> products = carts.get(userId);
        if (products != null) {
            products = products.stream().filter(p -> p.getId() != id).collect(Collectors.toList());
            carts.put(userId, products);
        }
    }

    public List<Product> getProducts(long userId) {
        return carts.get(userId);
    }
}
