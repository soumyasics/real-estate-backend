package com.example.RealEstate.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PropertyModel {

    private String pic;
    private double lat;
    private double log;
    private String district;
    private String city;
    private String type;
    private int isActive;
    private String landmark;
    private String features;
    private int price;
    private String area;
}
