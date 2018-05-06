package com.jaxi.service.impl;

import com.jaxi.entity.Config;
import com.jaxi.repository.ConfigRepository;
import com.jaxi.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "configs")
public class ConfigServiceImpl implements ConfigService {


    @Autowired
    private ConfigRepository configRepository;

    private static final Logger logger = LoggerFactory.getLogger(MerchantCategoryServiceImpl.class);

    @Override
    @Cacheable(key = "#key")
    public String get(String key) {
        logger.info(key);
        return ((Config)configRepository.findById(key).get()).getValue();
    }

    @Override
    @Cacheable(key = "#key")
    public String get(String key, String default1) {
        logger.info(key);
        Optional<Config> config = configRepository.findById(key);
        if(config.isPresent()) {
            return config.get().getValue();
        }else{
            return default1;
        }
    }
}
