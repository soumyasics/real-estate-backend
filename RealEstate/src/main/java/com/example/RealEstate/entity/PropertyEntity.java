package com.example.RealEstate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="property")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Long sid;
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
