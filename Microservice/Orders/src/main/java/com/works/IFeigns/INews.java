package com.works.IFeigns;

import com.works.props.News;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "newsapi", url = "https://newsapi.org/v2/")
public interface INews {

    @GetMapping("top-headlines?country=tr&category=business")
    News news(@RequestParam String apiKey);

}
