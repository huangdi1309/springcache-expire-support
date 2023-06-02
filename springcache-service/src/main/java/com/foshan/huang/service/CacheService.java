package com.foshan.huang.service;


import com.foshan.huang.bo.UserBO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheService {

    /**
     * 设置过期时间 100秒
     */
    @Cacheable(value = "spring.cache.testexpire#100000", key="'cache_custom_expire:' + #name")
    public UserBO getUser(String name) {
        String key = UUID.randomUUID().toString();
        return UserBO.builder().name(name).sex("male").avatar("http://xxxxxx").key(key).build();
    }

    /**
     * 没有设置过期时间
     */
    @Cacheable(value = "spring.cache.never_expire", key="'cache_never_expire:' + #name")
    public UserBO getUserWithoutExpire(String name) {
        String key = UUID.randomUUID().toString();
        return UserBO.builder().name(name).sex("male").avatar("http://xiaoming").key(key).build();
    }

}
