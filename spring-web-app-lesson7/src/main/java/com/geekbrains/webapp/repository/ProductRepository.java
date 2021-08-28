package com.geekbrains.webapp.repository;

import com.geekbrains.webapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceLessThanEqual(int maxPrice);

    List<Product> findAllByPriceGreaterThan(int minPrice);

    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

}
