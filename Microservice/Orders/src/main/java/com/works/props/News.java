package com.works.props;

import java.util.List;

@lombok.Data
public class News {
    private String status;
    private Long totalResults;
    private List<Article> articles;
}
