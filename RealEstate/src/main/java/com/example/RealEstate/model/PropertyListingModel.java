package com.example.RealEstate.model;

public class PropertyListingModel {
    private Long propertyId;
    private Long sellerId;
    private String pic;
    private Double lat;
    private Double log;
    private String district;
    private String city;
    private String type;
    private String landmark;
    private String features;
    private Integer price;
    private String area;
    private String sellerName;
    private String sellerEmail;

    public PropertyListingModel() {
    }

    public PropertyListingModel(Long propertyId, Long sellerId, String pic, Double lat, Double log, String district, String city, String type, String landmark, String features, Integer price, String area, String sellerName, String sellerEmail) {
        this.propertyId = propertyId;
        this.sellerId = sellerId;
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
        this.sellerName = sellerName;
        this.sellerEmail = sellerEmail;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLog() {
        return log;
    }

    public void setLog(Double log) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
}
