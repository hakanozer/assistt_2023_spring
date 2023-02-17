package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ProductCat {

    @Id
    private Long pid;

    private String title;
    private Integer price;
    private String name;

}
