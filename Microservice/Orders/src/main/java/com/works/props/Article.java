package com.works.props;

import java.time.OffsetDateTime;

@lombok.Data
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private OffsetDateTime publishedAt;
    private String content;
}
