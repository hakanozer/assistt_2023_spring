package com.works.entities;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Session")
public class UserSession extends BaseUser {


}