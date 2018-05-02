package com.jaxi.rest.controller;

import com.jaxi.common.CustomUserPrincipal;
import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Merchant;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class MerchantController {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/merchant/findAll")
    @PreAuthorize("hasAuthority('c')")
    public Page<Merchant> findAll(Pageable page, Authentication authentication){
        CustomUserPrincipal _principal = (CustomUserPrincipal) authentication.getPrincipal();
        Canvasser _canvasser  = (Canvasser) _principal.getUser();
        return merchantRepository.findAllByCanvasser(_canvasser,page);
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
