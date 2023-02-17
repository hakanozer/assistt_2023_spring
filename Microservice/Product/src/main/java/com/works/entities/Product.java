package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String title;
    private Integer price;

    @ManyToMany
    private List<Category> categories;

}
