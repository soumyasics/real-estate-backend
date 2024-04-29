package com.example.RealEstate.model;

import java.sql.Date;

public class MessageListingByBuyerIdModel {


        private String message;
        private Date date;
        private String messagefrom;

        public MessageListingByBuyerIdModel() {
        }

        public MessageListingByBuyerIdModel(String message, Date date, String messagefrom) {
            this.message = message;
            this.date = date;
            this.messagefrom = messagefrom;
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

        public String getMessagefrom() {
            return messagefrom;
        }

        public void setMessagefrom(String messagefrom) {
            this.messagefrom = messagefrom;
        }
    }


