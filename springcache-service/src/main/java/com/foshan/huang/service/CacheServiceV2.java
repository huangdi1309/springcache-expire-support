package com.foshan.huang.service;


import com.foshan.huang.annotations.TimeToLive;
import com.foshan.huang.bo.UserBO;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceV2 {

    /**
     * 设置过期时间180秒  使用Cacheable注解
     */
    @Cacheable(value = "spring.cache.testexpire", key="'cache_custom_expire:' + #name")
    @TimeToLive(ttl = 180, timeUnit = TimeUnit.SECONDS)
    public UserBO getUser(String name) {
        String key = UUID.randomUUID().toString();
        return UserBO.builder().name(name).sex("male").avatar("http://xxxxxx").key(key).build();
    }

    /**
     * 设置过期时间 180秒 使用CachePut注解
     */
    @CachePut(value = "spring.cache.testexpire", key="'cache_custom_expire:' + #name")
    @TimeToLive(ttl = 180, timeUnit = TimeUnit.SECONDS)
    public UserBO updateUser(String name) {
        String key = UUID.randomUUID().toString();
        return UserBO.builder().name(name).sex("female").avatar("http://xxxxxx").key(key).build();
    }

}
