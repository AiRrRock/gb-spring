package com.geekbrains.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private String title;
    private int price;
    private Long amount;

    public CartItemDto(ProductDto productDto, Long amount) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.price = productDto.getPrice();
        this.amount = amount;
    }
}
