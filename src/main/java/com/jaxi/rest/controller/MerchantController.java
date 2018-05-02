package com.jaxi.rest.controller;

import com.jaxi.common.CustomUserPrincipal;
import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import com.jaxi.repository.MerchantCategoryRepository;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;

    @GetMapping("/merchant/all")
    @PreAuthorize("hasAuthority('c')")
    public Page<Merchant> merchants(Pageable page, Authentication authentication){
        CustomUserPrincipal _principal = (CustomUserPrincipal) authentication.getPrincipal();
        Canvasser _canvasser  = (Canvasser) _principal.getUser();
        return merchantRepository.findAllByCanvasser(_canvasser,page);
    }


    @GetMapping("/merchant/category/all")
    @PreAuthorize("hasAuthority('c')")
    public List<MerchantCategory> categories(){
        return merchantCategoryRepository.findAll();
    }

    @PostMapping("/merchant/create")
    @PreAuthorize("hasAuthority('c')")
    public ResponseEntity create(@Valid @RequestBody Merchant merchant, Authentication authentication){

        CustomUserPrincipal _principal = (CustomUserPrincipal) authentication.getPrincipal();

        merchant.setCanvasser((Canvasser) _principal.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                merchantService.create(merchant)
        );
    }
}
