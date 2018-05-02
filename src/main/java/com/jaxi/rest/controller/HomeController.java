package com.jaxi.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
public class HomeController {

    @RequestMapping("/")
    public HashMap home(){

        HashMap _map = new HashMap();
        _map.put("timestamp",new Date());
        return _map;
    }
}
