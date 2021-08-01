package ru.gb.lesson2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Primary
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1, "A", 85),
                new Product(2, "B", 25),
                new Product(3, "C", 25),
                new Product(4, "D", 25),
                new Product(5, "E", 25)
        ));
    }

    public Product findById(long id) {
        return productList.stream().filter(i -> i.getId() == id ).findFirst().get();
    }

    public List<Product> findAll() {
        return productList;
    }

    public void save(Product item) {
        long newId = productList.stream().mapToLong(Product::getId).max().getAsLong() + 1;
        item.setId(newId);
        productList.add(item);
    }
}
