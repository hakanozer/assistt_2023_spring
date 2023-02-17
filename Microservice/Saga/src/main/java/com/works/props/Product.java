package com.works.props;

import java.util.List;

@lombok.Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private Double discountPercentage;
    private Double rating;
    private Long stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;
}
