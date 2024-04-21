package com.example.RealEstate.model;

public class OrderListByPropertyIdModel {
    private String area;
    private String city;
    private String district;
    private  String features;
    private Double orderPrice;

    public OrderListByPropertyIdModel() {
    }

    public OrderListByPropertyIdModel(String area, String city, String district, String features, Double orderPrice) {
        this.area = area;
        this.city = city;
        this.district = district;
        this.features = features;
        this.orderPrice = orderPrice;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
