package ru.gb.spring.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
