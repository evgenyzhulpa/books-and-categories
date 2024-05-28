package com.example.SpringBootRedis.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.cache")
public class AppCacheProperties {

    private final List<String> cacheNames = new ArrayList<>();
    private final HashMap<String, CacheProperties> caches = new HashMap<>();
    private CacheType cacheType;

    @Data
    public static class CacheProperties {
        private Duration expiry = Duration.ZERO;
    }

    public interface CacheNames {
        String BOOK_ENTITIES_BY_CATEGORY_NAME = "bookEntitiesByCategoryName";
        String BOOK_ENTITY_BY_NAME_AND_AUTHOR = "bookEntityByNameAndAuthor";
    }

    public enum CacheType {
        IN_MEMORY, REDIS
    }
}
