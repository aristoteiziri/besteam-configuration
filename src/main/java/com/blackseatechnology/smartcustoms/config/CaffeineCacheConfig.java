package com.blackseatechnology.smartcustoms.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {
	@Bean
	public Caffeine<?, ?> caffeineConfig() {
	    return Caffeine.newBuilder() 
	    		.initialCapacity(200)
	            .maximumSize(500)
	            .expireAfterWrite(60, TimeUnit.MINUTES);
	}
	
	@Bean
	public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
	    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
	    caffeineCacheManager.setCaffeine(caffeine);
	    return caffeineCacheManager;
	}
}
