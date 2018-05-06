package com.jaxi.service.impl;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import com.jaxi.entity.Product;
import com.jaxi.exception.MerchantCategoryNotFoundException;
import com.jaxi.repository.MerchantCategoryRepository;
import com.jaxi.repository.MerchantRepository;
import com.jaxi.repository.ProductRepository;
import com.jaxi.rest.controller.MerchantController;
import com.jaxi.service.MerchantService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Merchant create(Merchant merchant) {

        MerchantCategory _category = merchant.getCategory();

        if(_category !=null ){
            Optional<MerchantCategory> _oldCategory= merchantCategoryRepository.findById(_category.getId());
            if(_oldCategory.isPresent()){
                merchant.setCategory(_oldCategory.get());
            }else{
                throw new MerchantCategoryNotFoundException("category not found");
            }
        }

        Set<Product> _products = merchant.getProducts();

        merchant.setProducts(new HashSet<Product>());
        Merchant _newMerchant = merchantRepository.save(merchant);




        if(_products !=null ){
            for (Product _product: _products) {
                _product.setMerchant(_newMerchant);
                merchant.getProducts().add(productRepository.save(_product));
            }
        }

        Hibernate.initialize(_newMerchant.getProducts());
        Hibernate.initialize(_newMerchant.getCategory());
        return _newMerchant;

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
    public Product addProductImage(Image image, Long productId) {
        return null;
    }
}
