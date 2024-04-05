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
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "sid")
    private  Long sid;
    @Column(name = "pic")
    private String pic;
    @Column(name = "lat")
    private double lat;
    @Column(name = "log")
    private double log;
    @Column(name = "district")
    private String district;
    @Column(name = "city")
    private String city;
    @Column(name = "type")
    private String type;
    @Column(name = "isActive")
    private int isActive;
    @Column(name = "landmark")
    private String landmark;
    @Column(name = "features")
    private String features;
    @Column(name = "price")
    private int price;
    @Column(name = "area")
    private String area;

}
