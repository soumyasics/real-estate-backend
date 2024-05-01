package com.example.RealEstate.model;

import jakarta.persistence.Column;

public class ViewAllPropertiesModel {

    private Long id;

    private  Long sid;
   private String sellername;
    private String pic;

    private double lat;

    private double log;
    private String district;
    private String city;
    private String type;

    private String landmark;
    private String features;
    private int price;
    private String area;
    private String status;

    public ViewAllPropertiesModel() {
    }

    public ViewAllPropertiesModel(Long id, Long sid, String sellername, String pic, double lat, double log, String district, String city, String type, String landmark, String features, int price, String area, String status) {
        this.id = id;
        this.sid = sid;
        this.sellername = sellername;
        this.pic = pic;
        this.lat = lat;
        this.log = log;
        this.district = district;
        this.city = city;
        this.type = type;
        this.landmark = landmark;
        this.features = features;
        this.price = price;
        this.area = area;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
