package com.jaxi.service;

import com.jaxi.entity.Image;
import com.jaxi.entity.Product;

public interface ProductService {

    Image addImage(Image image , Long productId);
}
