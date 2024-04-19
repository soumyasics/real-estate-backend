package com.example.RealEstate.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "buyermessage")
public class MessageRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "buyerid")
    private Long buyerid;
    @Column(name = "sellerid")
    private Long sellerid;
    @Column(name = "propertyid")
    private Long propertyid;
    @Column(name = "message")
    private String message;
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    public MessageRequestEntity() {
    }

    public MessageRequestEntity(Long id, Long buyerid, Long sellerid, Long propertyid, String message, Date date) {
        this.id = id;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.propertyid = propertyid;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Long buyerid) {
        this.buyerid = buyerid;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Long getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(Long propertyid) {
        this.propertyid = propertyid;
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
}

