package com.example.finapp.SharedContext.Config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for caching in the application.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Creates a cache manager for the application.
     *
     * @return the cache manager
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("financialAdvice");
    }
}

