package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.CategoryService;
import com.geekbrains.webapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    @GetMapping("/products")
    public List<ProductDto> findAllFiltered(@RequestParam(name = "max_price") @Nullable Integer maxPrice,
                                            @RequestParam(name = "min_price") @Nullable Integer minPrice) {
        List<ProductDto> productDtos = new ArrayList<>();
        List<Product> products;
        if (minPrice != null) {
            if (maxPrice != null) {
                products = productService.findByPriceInInterval(minPrice, maxPrice);
            } else {
                products = productService.findByPriceGreaterThan(minPrice);
            }
        } else if (maxPrice != null) {
            products = productService.findByPriceLessThan(maxPrice);
        } else {
            products = productService.findAll();
        }
        for (Product p : products) {
            productDtos.add(new ProductDto(p));
        }
        return productDtos;
    }

    @PostMapping("/products")
    public ProductDto add(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).get();
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("/products/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
