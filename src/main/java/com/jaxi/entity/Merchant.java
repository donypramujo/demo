package com.jaxi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Merchant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false,length = 100)
    @NotNull
    private String name;

    @Column(nullable = false,length = 20)
    @NotNull
    @Pattern(regexp="\\+?([ -]?\\d+)+|\\(\\d+\\)([ -]\\d+)")
    private String phoneNumber;

    @Column(length = 100)
    @Email
    private String email;

    @Column(length = 100)
    private String ktpNumber;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    @JsonIgnore
    private Canvasser canvasser;

    @ManyToOne
    private Image image;

    @ManyToOne
    private MerchantCategory category;

    @Enumerated(EnumType.STRING)
    private MerchantType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MerchantCategory getCategory() {
        return category;
    }

    public void setCategory(MerchantCategory category) {
        this.category = category;
    }

    public String getKtpNumber() {
        return ktpNumber;
    }

    public void setKtpNumber(String ktpNumber) {
        this.ktpNumber = ktpNumber;
    }

    public MerchantType getType() {
        return type;
    }

    public void setType(MerchantType type) {
        this.type = type;
    }

    public Canvasser getCanvasser() {
        return canvasser;
    }

    public void setCanvasser(Canvasser canvasser) {
        this.canvasser = canvasser;
    }
}
