package com.jaxi.service.impl;

import com.jaxi.entity.Image;
import com.jaxi.entity.Merchant;
import com.jaxi.entity.MerchantCategory;
import com.jaxi.entity.Product;
import com.jaxi.exception.MerchantAlreadyExistException;
import com.jaxi.exception.MerchantCategoryNotFoundException;
import com.jaxi.repository.*;
import com.jaxi.rest.controller.MerchantController;
import com.jaxi.service.MerchantService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Merchant create(Merchant merchant) {

        Optional<Merchant> _oldMerchant =  merchantRepository.findById(merchant.getId());

        if(_oldMerchant.isPresent()){
            throw  new MerchantAlreadyExistException("merchant already exists");
        }

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
                _newMerchant.getProducts().add(productRepository.save(_product));
            }
        }

        Hibernate.initialize(_newMerchant.getProducts());
        Hibernate.initialize(_newMerchant.getCategory());
        return _newMerchant;

    }

    @Override
    public Image addImage(Image image , String merchantId){
       Optional<Merchant> merchant = merchantRepository.findById(merchantId);
       if(merchant.isPresent()){
           merchant.get().setImage(imageRepository.save(image));
           merchantRepository.save(merchant.get());
       }
       return merchant.get().getImage();
    }


}
