package hzh.dataanalytics.configuration;

import hzh.dataanalytics.handler.HaCacheErrorHandler;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Collections;

@Configuration
public class CacheConfiguration extends CachingConfigurerSupport implements CachingConfigurer {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singletonList(
                new ConcurrentMapCache("tables")));
        return cacheManager;
    }

    @Override
    @Nullable
    public CacheErrorHandler errorHandler() {
        return new HaCacheErrorHandler();
    }
}
