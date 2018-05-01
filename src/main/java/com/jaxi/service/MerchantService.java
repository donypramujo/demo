package com.jaxi.service;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;

public interface MerchantService {

    Merchant create(Merchant merchant);

    Merchant linkImage(Image image , Long merchantId);
}
