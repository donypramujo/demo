package com.jaxi.service;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.Product;

public interface MerchantService {

    Merchant create(Merchant merchant);

    Merchant addImage(Image image , Long merchantId);

    Product addProduct(Product product, Long merchantId);

    Product addProductImage(Image image, Long productId);
}
