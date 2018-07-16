package com.jaxi.rest.controller;

import com.jaxi.common.JaxiUserPrincipal;
import com.jaxi.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public User profile( Authentication authentication){

        JaxiUserPrincipal _principal = (JaxiUserPrincipal) authentication.getPrincipal();
        User user  = _principal.getUser();

        return user;
    }
}
