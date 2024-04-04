package com.example.RealEstate.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class PropertyModel {
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

    public PropertyModel() {
    }

    public PropertyModel(Long sid, String pic, double lat, double log, String district, String city, String type, int isActive, String landmark, String features, int price, String area) {
        this.sid = sid;
        this.pic = pic;
        this.lat = lat;
        this.log = log;
        this.district = district;
        this.city = city;
        this.type = type;
        this.isActive = isActive;
        this.landmark = landmark;
        this.features = features;
        this.price = price;
        this.area = area;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
}
