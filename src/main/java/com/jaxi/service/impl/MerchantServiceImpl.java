package com.jaxi.service.impl;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import com.jaxi.entity.Product;
import com.jaxi.repository.MerchantCategoryRepository;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;

    @Override
    public Merchant create(Merchant merchant) {

        MerchantCategory _category = merchant.getCategory();

        if(_category !=null ){
            MerchantCategory _oldCategory= merchantCategoryRepository.findByName(_category.getName());
            if(_oldCategory == null){
                merchantCategoryRepository.save(_category);
            }else{
				merchant.setCategory(_oldCategory);
			}
        }

        return merchantRepository.save(merchant);

    }
    @Override
    public Merchant addImage(Image image , Long merchantId){

       Merchant merchant = merchantRepository.findById(merchantId).get();

       if(merchant != null && image != null){
           merchant.setImage(image);
           return merchantRepository.save(merchant);
       }

       return null;
    }

    @Override
    public Product addProduct(Product product, Long merchantId) {
        return null;
    }

    @Override
    public Product addProductImage(Image image, Long productId) {
        return null;
    }
}
