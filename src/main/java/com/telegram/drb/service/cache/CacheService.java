package com.telegram.drb.service.cache;

/**
 * Custom cache interface.
 *
 * @param <K> key
 * @param <V> value
 * @author Valentyn Korniienko
 */
public interface CacheService<K, V> {

    /**
     * Get value by key from cache.
     *
     * @param key the key
     * @return value
     */
    V get(K key);

    /**
     * Put value to the cache.
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Clear cache.
     */
    void clear();

}
