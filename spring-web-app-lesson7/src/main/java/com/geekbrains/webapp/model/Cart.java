package com.geekbrains.webapp.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Map<Long, Long> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void addProduct(Long productId) {
        products.computeIfPresent(productId, (key, val) -> val + 1);
        products.putIfAbsent(productId, 1L);
    }

    public void removeProduct(Long productId) {
        Long numberOfProduct = products.get(productId);
        if (numberOfProduct != null) {
            numberOfProduct = numberOfProduct - 1;
            if (numberOfProduct <= 0) {
                products.remove(productId);
            } else {
                products.put(productId, numberOfProduct);
            }
        }
    }

    public void deleteProduct(Long productId) {
        products.remove(productId);
    }


}
