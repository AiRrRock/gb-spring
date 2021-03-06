package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.CategoryDto;
import com.geekbrains.webapp.exception.ResourceNotFoundException;
import com.geekbrains.webapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return new CategoryDto(categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found")));
    }
}
