package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.exception.ResourceNotFoundException;
import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.CategoryService;
import com.geekbrains.webapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
       /* Optional<Product> product = productService.findById(id);
        return product.map(value -> new ResponseEntity(new ProductDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new MarketError("Product with id = " + id + "not found"), HttpStatus.NOT_FOUND));*/
        return new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id = " + id + "not found")));
    }

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex,
                                    @RequestParam(name = "max_price") @Nullable Integer maxPrice,
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

        if (pageIndex < 1) {
            pageIndex = 1;
        }

        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }


    @PostMapping
    public ProductDto add(@RequestBody ProductDto productDto) {
        Product newProduct = new Product();
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("The category mentioned doesn't exist"));
        newProduct.setCategory(category);
        newProduct.setPrice(productDto.getPrice());
        newProduct.setTitle(productDto.getTitle());
        productService.save(newProduct);
        return new ProductDto(newProduct);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        Long id = productDto.getId();
        if (id != null) {
            Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id =" + id + "doesn't exist"));
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("The category mentioned doesn't exist"));
            product.setCategory(category);
            product.setPrice(productDto.getPrice());
            product.setTitle(productDto.getTitle());
            productService.save(product);
            return new ProductDto(product);
        }
        throw new ResourceNotFoundException("Product with id =" + id + "doesn't exist");
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @DeleteMapping
    public void delete() {
        productService.deleteAll();
    }


}
