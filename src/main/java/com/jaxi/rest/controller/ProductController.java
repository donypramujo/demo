package com.jaxi.rest.controller;

import com.jaxi.entity.Image;
import com.jaxi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/product/addImage")
    @PreAuthorize("hasAuthority('cv')")
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
