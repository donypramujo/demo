package com.jaxi.rest.controller;

import com.jaxi.entity.Merchant;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/merchant/findAll")
    public Page<Merchant> findAll(Pageable page){
        return merchantRepository.findAll(page);
    }

    @PostMapping("/merchant/create")
    public ResponseEntity create(@Valid @RequestBody Merchant merchant){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                merchantService.create(merchant)
        );
    }
}
