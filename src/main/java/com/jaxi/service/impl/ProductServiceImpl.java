package com.jaxi.service.impl;

import com.jaxi.entity.Image;
import com.jaxi.entity.Product;
import com.jaxi.repository.ImageRepository;
import com.jaxi.repository.ProductRepository;
import com.jaxi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image addImage(Image image, Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            product.get().setImage( imageRepository.save(image));
            productRepository.save(product.get());
        }
        return product.get().getImage();
    }
}
