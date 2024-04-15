package com.example.RealEstate.model;

public class PropertyAndSellerModel {
    public PropertyAndSellerModel() {
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

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerLastname() {
        return sellerLastname;
    }

    public void setSellerLastname(String sellerLastname) {
        this.sellerLastname = sellerLastname;
    }

    public int getSellerage() {
        return sellerage;
    }

    public void setSellerage(int sellerage) {
        this.sellerage = sellerage;
    }

    public String getSellerdob() {
        return sellerdob;
    }

    public void setSellerdob(String sellerdob) {
        this.sellerdob = sellerdob;
    }

    public String getSellergender() {
        return sellergender;
    }

    public void setSellergender(String sellergender) {
        this.sellergender = sellergender;
    }

    public Long getSellerphone() {
        return sellerphone;
    }

    public void setSellerphone(Long sellerphone) {
        this.sellerphone = sellerphone;
    }

    public String getSelleraddress() {
        return selleraddress;
    }

    public void setSelleraddress(String selleraddress) {
        this.selleraddress = selleraddress;
    }

    public String getSellerusername() {
        return sellerusername;
    }

    public void setSellerusername(String sellerusername) {
        this.sellerusername = sellerusername;
    }

    public PropertyAndSellerModel(String pic, Double lat, Double log, String district, String city, String type, String landmark, String features, Integer price, String area, String sellerFirstName, String sellerEmail, String sellerLastname, int sellerage, String sellerdob, String sellergender, Long sellerphone, String selleraddress, String sellerusername) {
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
        this.sellerFirstName = sellerFirstName;
        this.sellerEmail = sellerEmail;
        this.sellerLastname = sellerLastname;
        this.sellerage = sellerage;
        this.sellerdob = sellerdob;
        this.sellergender = sellergender;
        this.sellerphone = sellerphone;
        this.selleraddress = selleraddress;
        this.sellerusername = sellerusername;
    }

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
    private String sellerFirstName;
    private String sellerEmail;


    private String sellerLastname;
    private int sellerage;
    private String sellerdob;
    private String sellergender;
    private Long sellerphone;

    private String selleraddress;
    private String sellerusername;




}
