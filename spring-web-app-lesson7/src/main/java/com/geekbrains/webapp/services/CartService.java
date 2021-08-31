package com.geekbrains.webapp.services;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.model.Cart;
import com.geekbrains.webapp.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private Map<Long, Cart> carts;
    @Autowired
    private ProductService productService;

    @PostConstruct
    private void init() {
        this.carts = new HashMap<>();
        //To be removed
        Cart cart = new Cart();
        carts.put(1L, cart);
    }

    public void addProduct(Long cartId, Long productId) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.addProduct(productId);
        }
    }


    public void deleteProduct(Long cartId, Long productId) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.deleteProduct(productId);
        }
    }

    public void removeInstanceOfProduct(Long cartId, Long productId) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            cart.removeProduct(productId);
        }
    }

    public Map<ProductDto, Long> findAll(Long cartId) {
        Cart cart = carts.get(cartId);
        if (cart != null) {
            Map<ProductDto, Long> productsInCart = new HashMap<>();
            for (Map.Entry<Long, Long> items : cart.getProducts().entrySet()) {
                Optional<Product> product = productService.findById(items.getKey());
                product.ifPresent(value -> productsInCart.put(new ProductDto(value), items.getValue()));
            }
            return productsInCart;
        }
        return Collections.emptyMap();
    }


}
