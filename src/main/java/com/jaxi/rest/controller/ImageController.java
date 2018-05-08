package com.jaxi.rest.controller;

import com.jaxi.entity.Image;
import com.jaxi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ImageController {


    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/image/{imageId}")
    @PreAuthorize("hasAuthority('c')")
    public ResponseEntity<Resource> image(@PathVariable("imageId") Long imageId) {
        Optional<Image> _image = imageRepository.findById(imageId);
        if(!_image.isPresent()){
            throw new RuntimeException("File not found");
        }

        ByteArrayResource _bar =new ByteArrayResource(_image.get().getBytes());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,_image.get().getContentType()).body(_bar);
    }


}
