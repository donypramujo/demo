package com.jaxi.service.impl;

import com.jaxi.entity.MerchantCategory;
import com.jaxi.repository.MerchantCategoryRepository;
import com.jaxi.service.MerchantCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "merchantCategories")
public class MerchantCategoryServiceImpl implements MerchantCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantCategoryServiceImpl.class);

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;


    @Override
    @Cacheable
    public List<MerchantCategory> findAll() {
        logger.info("load merchant categories");
        return merchantCategoryRepository.findAll();
    }
}
