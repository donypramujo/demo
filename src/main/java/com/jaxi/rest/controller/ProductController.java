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
import com.jaxi.service.ProductService;
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
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/product/addImage")
    @PreAuthorize("hasAuthority('c')")
    public ResponseEntity addImage(@RequestParam("image") MultipartFile file,@RequestParam("productId") Long productId)throws IOException {
        logger.info("uploading file ");

        Image _image= new Image();
        _image.setBytes(file.getBytes());
        _image.setSize(file.getSize());
        _image.setContentType(file.getContentType());
        _image.setName(file.getName());
        _image.setOriginalFileName(file.getOriginalFilename());

        return ResponseEntity.ok().body( productService.addImage(_image,productId));
    }
}
