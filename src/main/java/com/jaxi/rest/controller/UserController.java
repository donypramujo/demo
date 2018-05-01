package com.jaxi.rest.controller;

import com.jaxi.entity.User;
import com.jaxi.exception.UserAlreadyExistException;
import com.jaxi.repository.UserRepository;
import com.jaxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("user/findAll")
    public List<User> findAll(){
       return userRepository.findAll();
    }


    @PostMapping("user/create")
    public  ResponseEntity create(@Valid @RequestBody User user){

        final User _user = userRepository.findByEmail(user.getEmail());
        if(_user != null){
           throw new UserAlreadyExistException("User Already Exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( userRepository.save(user));
    }

}
