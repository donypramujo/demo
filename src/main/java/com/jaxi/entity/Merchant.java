package com.jaxi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Merchant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;

    private String phoneNumber;

    private String email;

    private String nik;

    private Company company;

    private Double latitude;

    private Double longitude;

    private MerchantImage merchantImage;

    private GameImage gameImage;

    private MerchantCategory category;
}
