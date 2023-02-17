package com.works.entities;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;


@RedisHash("CustomerRedis")
@Data
public class CustomerRedis {

    @Id
    private String id;

    private Long customerID;
    private String email;
    private Long startTime;
    private Long endTime;


}
