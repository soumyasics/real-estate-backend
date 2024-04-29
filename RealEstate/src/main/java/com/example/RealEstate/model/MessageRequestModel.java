package com.example.RealEstate.model;

import java.sql.Date;

public class MessageRequestModel {
    private Long buyerId;
    private Long sellerId;
    private Long propertyId;
    private String message;
    private Date date;
private String messagefrom;

    public MessageRequestModel() {
    }

    public MessageRequestModel(Long buyerId, Long sellerId, Long propertyId, String message, Date date, String messagefrom) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.propertyId = propertyId;
        this.message = message;
        this.date = date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getmessagefrom() {
        return messagefrom;
    }

    public void setmessagefrom(String messagefrom) {
        this.messagefrom = messagefrom;
    }
}
