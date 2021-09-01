package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.CartItemDto;
import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.services.CartService;
import com.geekbrains.webapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CategoryService categoryService;

    @GetMapping
    public List<CartItemDto> findAll(@RequestParam(name = "cId") Long cartId) {
        List<CartItemDto> dtos = new ArrayList<>();
        for (Map.Entry<ProductDto, Long> productDtoLongEntry : cartService.findAll(cartId).entrySet()) {
            dtos.add(new CartItemDto(productDtoLongEntry.getKey(), productDtoLongEntry.getValue()));
        }
        return dtos;
    }

    @GetMapping("/add")
    public void increment(@RequestParam(name = "cId") Long cartId, @RequestParam(name = "pId") Long productId) {
        cartService.addProduct(cartId, productId);
    }

    @GetMapping("/dec")
    public void decrement(@RequestParam(name = "cId") Long cartId, @RequestParam(name = "pId") Long productId) {
        cartService.removeInstanceOfProduct(cartId, productId);
    }

    @DeleteMapping()
    public void delete(@RequestParam(name = "cId") Long cartId, @RequestParam(name = "pId") Long productId) {
        cartService.deleteProduct(cartId, productId);
    }

}
