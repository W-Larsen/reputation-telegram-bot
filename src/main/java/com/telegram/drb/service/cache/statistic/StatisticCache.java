package com.telegram.drb.service.cache.statistic;

import com.telegram.drb.model.cache.StatisticCacheBuilder;
import com.telegram.drb.service.cache.CacheService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Statistic cache implementation of custom cache interface.
 * Used to store reputation change statistic.
 *
 * @param <K> the key
 * @param <V> the value
 * @author Valentyn Korniienko
 */
public final class StatisticCache<K, V> implements CacheService<K, V> {

    private int initialCapacity;
    private String expirationByCron;

    private Map<K, V> statisticCache = new ConcurrentHashMap<>(initialCapacity);

    public StatisticCache(StatisticCacheBuilder<? super K, ? super V> builder) {
        this.initialCapacity = builder.getInitialCapacity();
        this.expirationByCron = builder.getExpirationByCron();

        /*
        Initialization evicting scheduler
         */
        initScheduledEvicting();
    }

    @Override
    public V get(K key) {
        return statisticCache.get(key);
    }

    @Override
    public void put(K key, V value) {
        statisticCache.put(key, value);
    }

    @Override
    public void clear() {
        statisticCache.clear();
    }

    private void initScheduledEvicting() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(this::clear, new CronTrigger(expirationByCron));
    }

}
