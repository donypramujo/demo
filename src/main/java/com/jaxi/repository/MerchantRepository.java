package com.jaxi.repository;

import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository <Merchant,Long> {
    Page<Merchant> findAllByCanvasser(Canvasser canvasser, Pageable pageable);
}
