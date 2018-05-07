package com.jaxi.repository;

import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Merchant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MerchantRepository extends JpaRepository <Merchant,String> {
    Page<Merchant> findAllByCanvasser(Canvasser canvasser, Pageable pageable);

    long countByCanvasserAndCreatedDateBetween(Canvasser canvasser,Date from, Date to);
}
