package com.telegram.drb.model.cache;

import com.telegram.drb.service.cache.statistic.StatisticCache;

/**
 * Statistic cache builder.
 *
 * @param <K> the key
 * @param <V> the value
 */
public final class StatisticCacheBuilder<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int initialCapacity = DEFAULT_INITIAL_CAPACITY;
    private String expirationByCron;

    private StatisticCacheBuilder() {
    }

    /**
     * Create new statistic cache builder.
     *
     * @return statistic cache builder
     */
    public static StatisticCacheBuilder<Object, Object> newBuilder() {
        return new StatisticCacheBuilder<>();
    }

    /**
     * Gets initial capacity.
     *
     * @return initial capacity value
     */
    public int getInitialCapacity() {
        return initialCapacity;
    }


    /**
     * Gets expiration by cron value.
     *
     * @return expiration by cron value
     */
    public String getExpirationByCron() {
        return expirationByCron;
    }

    /**
     * Sets initial capacity.
     *
     * @param initialCapacity the initial capacity
     * @return statistic cache builder
     */
    public StatisticCacheBuilder<K, V> initialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        return this;
    }

    /**
     * Sets expiration cron.
     *
     * @param cron the cron value
     * @return statistic cache builder
     */
    public StatisticCacheBuilder<K, V> expirationByCron(String cron) {
        this.expirationByCron = cron;
        return this;
    }

    /**
     * Build statistic cache.
     *
     * @return statistic cache
     */
    public <K1 extends K, V1 extends V> StatisticCache<K1, V1> build() {
        return new StatisticCache<>(this);
    }

}
