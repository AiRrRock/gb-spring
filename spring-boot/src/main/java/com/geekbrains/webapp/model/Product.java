package com.geekbrains.webapp.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productWithOrders", query = "SELECT p FROM Product p JOIN FETCH p.orders WHERE p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    public Product() {
    }

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
