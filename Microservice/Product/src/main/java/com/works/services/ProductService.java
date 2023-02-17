package com.works.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.works.entities.Product;
import com.works.entities.UserSession;
import com.works.projections.IProJoinCatProjection;
import com.works.props.ProJoinCat;
import com.works.props.Products;
import com.works.repositories.ProductCatRepository;
import com.works.repositories.ProductRepository;
import com.works.repositories.UserSessionRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final DiscoveryClient discoveryClient;
    final RestTemplate restTemplate;
    final ProductCatRepository productCatRepository;
    final ObjectMapper objectMapper;
    final CacheManager cacheManager;


    @HystrixCommand(fallbackMethod = "fallBack",
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            ignoreExceptions = { ArithmeticException.class }
    )
    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        //int i = 1 / 0;
        //loginControl();
        try {
            //Thread.sleep(1100);
            hm.put(REnum.status, true);
            hm.put(REnum.result, productRepository.save(product));
            cacheManager.getCache("product").clear();

        }catch (Exception ex) {
            System.err.println("Redis Error : " + ex);
        }

        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity fallBack( Product product ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, "Server Fail");
        hm.put(REnum.result, product);
        return new ResponseEntity(hm, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity allProdut() {
        String url = "https://dummyjson.com/products";
        Products products = restTemplate.getForObject(url, Products.class);
        for(com.works.props.Product item : products.getProducts()) {
            item.setPrice( item.getPrice() + 10 );
        }
        return new ResponseEntity( products, HttpStatus.OK );
    }


    public String loginControl() {
        List<ServiceInstance> ls = discoveryClient.getInstances("loginx");
        ls.get(0).getUri();
        if ( ls != null && ls.size() > 0 ) {
            ServiceInstance instance = ls.get(0);
            String url = instance.getUri().toString();
            return url;
        }
        return "";
    }


    // single product
    public ResponseEntity singleProduct( Long pid ) {
        Optional<Product> optionalProduct = productRepository.findById(pid);
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (optionalProduct.isPresent() ) {
            hm.put(REnum.status, true);
            hm.put(REnum.result, optionalProduct.get());
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Not Found");
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    @Cacheable("product")
    public ResponseEntity list( Long cid, int pageCount ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);

        Sort sort = Sort.by("price").ascending();
        Pageable pageable = PageRequest.of(pageCount,3, sort);

        //hm.put(REnum.result, productRepository.findAll() );
        //hm.put(REnum.result, productCatRepository.proCat() );
        Page<IProJoinCatProjection> pageList = productRepository.proCatJoin(cid, pageable);
        List<ProJoinCat> lsx = objectMapper.convertValue( pageList.getContent(), List.class );

        hm.put(REnum.result, pageList );

        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
