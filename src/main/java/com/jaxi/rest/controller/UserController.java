package com.jaxi.rest.controller;

import com.jaxi.entity.User;
import com.jaxi.exception.UserAlreadyExistException;
import com.jaxi.repository.UserRepository;
import com.jaxi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;


//    @GetMapping("user/all")
//    @Cacheable("users")
    public List<User> findAll(){

        simulateSlowService();
        logger.info("agak lemot coy");
        return userRepository.findAll();
    }





    // Don't do this at home
    private void simulateSlowService() {
        logger.info("agak lemot coy xxx");
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


    //@PostMapping("user/create")
    public  ResponseEntity create(@Valid @RequestBody User user){

        final User _user = userRepository.findByEmail(user.getEmail());
        if(_user != null){
           throw new UserAlreadyExistException("User Already Exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( userRepository.save(user));
    }

}
