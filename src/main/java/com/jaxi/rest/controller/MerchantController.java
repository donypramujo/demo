package com.jaxi.rest.controller;

import com.jaxi.common.JaxiUserPrincipal;
import com.jaxi.entity.Canvasser;
import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.service.ConfigService;
import com.jaxi.service.MerchantCategoryService;
import com.jaxi.service.MerchantService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class MerchantController {

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantCategoryService merchantCategoryService;


    @Autowired
    private ConfigService configService;

    @GetMapping("/merchant/all")
    @PreAuthorize("hasAuthority('c')")
    public Page<Merchant> merchants(Pageable page, Authentication authentication){
        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        Canvasser _canvasser  = (Canvasser) _principal.getUser();
        return merchantRepository.findAllByCanvasser(_canvasser,page);
    }

    @GetMapping("/merchant/result/{date}")
    @PreAuthorize("hasAuthority('c')")
    public HashMap<String, Object> results(@PathVariable("date") @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Authentication authentication){

        logger.info(date.toString());

        HashMap<String,Object> result = new HashMap<>();
        Long expectedDaily = NumberUtils.parseNumber(configService.get("expected_daily"),Long.class);
        Long expectedMonthly = NumberUtils.parseNumber(configService.get("expected_montly"),Long.class);

        result.put("expected_daily",expectedDaily);
        result.put("expected_monthly",expectedMonthly);

        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        Canvasser _canvasser  = (Canvasser) _principal.getUser();


        DateTime _fromDay = new DateTime(date).withTime(0,0,0,0);
        DateTime _toDay = new DateTime(date).withTime(23,59,59,999);



        DateTime _fromMonth = new DateTime(date).withDayOfMonth(1).withTime(0,0,0,0);
        DateTime _toMonth = new DateTime(date).withTime(23,59,59,999).dayOfMonth().withMaximumValue();

        long resultDaily = merchantRepository.countByCanvasserAndCreatedDateBetween(_canvasser
                ,_fromDay.toDate()
                , _toDay.toDate());

        long resultMontly = merchantRepository.countByCanvasserAndCreatedDateBetween(_canvasser
                ,_fromMonth.toDate()
                ,_toMonth.toDate());


        result.put("result_daily",resultDaily);
        result.put("result_monthly",resultMontly);
        return result;
    }


    @GetMapping("/merchant/category/all")
    @PreAuthorize("hasAuthority('c')")
    public List<MerchantCategory> categories(){
        return merchantCategoryService.findAll();
    }


    @PostMapping("/merchant/create")
    @PreAuthorize("hasAuthority('c')")
    public ResponseEntity create(@Valid @RequestBody Merchant merchant, Authentication authentication){

        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();

        merchant.setCanvasser((Canvasser) _principal.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                merchantService.create(merchant)
        );
    }


    @PostMapping("/merchant/addImage")
    @PreAuthorize("hasAuthority('c')")
    public ResponseEntity addImage(@RequestParam("image") MultipartFile file,@RequestParam("merchantId") String merchantId)throws IOException {
        logger.info("uploading file ");

        Image _image= new Image();
        _image.setBytes(file.getBytes());
        _image.setSize(file.getSize());
        _image.setContentType(file.getContentType());
        _image.setName(file.getName());
        _image.setOriginalFileName(file.getOriginalFilename());

        return ResponseEntity.ok().body( merchantService.addImage(_image,merchantId));
    }
}
