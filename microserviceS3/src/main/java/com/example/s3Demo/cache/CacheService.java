package com.example.s3Demo.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Service class for caching objects using Caffeine cache.
 * This service allows storing, retrieving, and removing objects from the cache.
 */
@Component
public class CacheService {

    /**
     * The cache instance configured to expire entries 45 minutes after the last access.
     */
    private final Cache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterAccess(45, TimeUnit.MINUTES)
            .build();

    /**
     * Stores an object in the cache with the specified key.
     * If the cache previously contained a value associated with this key,
     * the old value is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * Retrieves an object from the cache associated with the specified key.
     * If the cache contains no value for this key, returns {@code null}.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this cache contains no mapping for the key
     */
    public Object get(String key) {
        return cache.getIfPresent(key);
    }

    /**
     * Removes the value associated with the specified key from the cache, if it is present.
     *
     * @param key the key whose mapping is to be removed from the cache
     */
    public void remove(String key) {
        cache.invalidate(key);
    }
}
