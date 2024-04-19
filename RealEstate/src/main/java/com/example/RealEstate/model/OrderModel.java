package com.example.RealEstate.model;

public class OrderModel {
    private Long buyerId;
    private Long sellerId;
    private Long propertyId;
    private double price;

    public OrderModel() {
    }

    public OrderModel(Long buyerId, Long sellerId, Long propertyId, double price) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.propertyId = propertyId;
        this.price = price;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
