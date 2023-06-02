package com.foshan.huang.config;

import com.foshan.huang.annotations.TimeToLive;
import com.foshan.huang.aspject.CacheTtlAspject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CustomizerRedisCacheManager extends RedisCacheManager {

    private final CacheTtlAspject cacheTtlAspject;

    public CustomizerRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration redisCacheConfiguration,
                                       CacheTtlAspject cacheTtlAspject) {
        super(cacheWriter, redisCacheConfiguration);
        this.cacheTtlAspject = cacheTtlAspject;
    }

    @Override
    public RedisCache createRedisCache(String cacheName, RedisCacheConfiguration redisCacheConfiguration) {
        Method method = cacheTtlAspject.getCacheNameMethodCache().get(cacheName);
        if (method != null) {
            TimeToLive timeToLive = AnnotationUtils.findAnnotation(method, TimeToLive.class);
            assert timeToLive != null;
            Duration expirationTime = getExpirationTime(cacheName, timeToLive.timeUnit(), timeToLive.ttl());
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(expirationTime);
        }

        return super.createRedisCache(cacheName, redisCacheConfiguration);
    }

    private Duration getExpirationTime(String cacheName, TimeUnit timeUnit, long ttl) {
        try {
            long second = timeUnit.toSeconds(ttl);
            return Duration.ofSeconds(second);
        } catch (Exception e) {
            log.error("解析指定cacheName:{},缓存失效时间异常", cacheName, e);
        }

        // 如果解析失败，直接按默认过期时间，一般是永不过期
        return Duration.ZERO;
    }


}


