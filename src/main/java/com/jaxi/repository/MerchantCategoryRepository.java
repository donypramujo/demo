package com.jaxi.repository;

import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantCategoryRepository extends JpaRepository<MerchantCategory,Long> {

    MerchantCategory findByName(String name);
}
