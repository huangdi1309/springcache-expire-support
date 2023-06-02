package com.foshan.huang.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @Description RedisCacheManager
 * @Author huangd
 **/
@Component
@Slf4j
public class CustomizerRedisCacheManager extends RedisCacheManager {

    private static final char SEPARATOR = '#';  // 过期时间分隔符，可以用其他符号

    public CustomizerRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration redisCacheConfiguration) {
        super(cacheWriter, redisCacheConfiguration);
    }


    @Override
    public RedisCache createRedisCache(String cacheName, RedisCacheConfiguration redisCacheConfiguration) {
        int expireIndex = cacheName.indexOf(SEPARATOR);
        if (expireIndex > 0) {
            long defaultExpiration = getExpirationTime(cacheName, expireIndex);
            cacheName = cacheName.substring(0, expireIndex);
            if (defaultExpiration > 0) {
                redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMillis(defaultExpiration));
            }
        }

        return super.createRedisCache(cacheName, redisCacheConfiguration);
    }

    /**
     * 缓存时间
     * @param name cacheName cache#3600
     * @param expireIndex
     * @return
     */
    protected long getExpirationTime(String name, int expireIndex) {
        String expirationAsString = name.substring(expireIndex + 1);
        try {
            return NumberUtils.toLong(expirationAsString);
        } catch (Exception e) {
            log.error("获取指定cacheName:{},缓存失效时间异常", name, e);
        }

        // 如果解析失败，直接按默认过期时间，一般是永不过期
        return 0;
    }

}


