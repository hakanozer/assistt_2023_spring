package com.works.props;

import java.util.List;

@lombok.Data
public class Products {
    private List<Product> products;
    private Long total;
    private Long skip;
    private Long limit;
}
