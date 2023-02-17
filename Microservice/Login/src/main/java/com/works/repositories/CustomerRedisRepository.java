package com.works.repositories;

import com.works.entities.CustomerRedis;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRedisRepository extends CrudRepository<CustomerRedis, String> {

}
