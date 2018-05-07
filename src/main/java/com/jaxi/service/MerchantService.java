package com.jaxi.service;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.Product;

public interface MerchantService {

    Merchant create(Merchant merchant);

    Image addImage(Image image , String merchantId);

}
