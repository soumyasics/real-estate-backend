package com.example.RealEstate.model;

import java.sql.Date;

public class MessageListingModel {
    private Long buyerId;
    private Long sellerId;
    private Long propertyId;
    private Date date;
    private String message;
    private String messagefrom;

    public MessageListingModel() {
    }

    public MessageListingModel(Long buyerId, Long sellerId, Long propertyId, Date date, String message, String messagefrom) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.propertyId = propertyId;
        this.date = date;
        this.message = message;
        this.messagefrom = messagefrom;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessagefrom() {
        return messagefrom;
    }

    public void setMessagefrom(String messagefrom) {
        this.messagefrom = messagefrom;
    }
}
