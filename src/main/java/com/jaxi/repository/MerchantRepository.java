package com.jaxi.repository;

import com.jaxi.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository <Merchant,Long> {
}
