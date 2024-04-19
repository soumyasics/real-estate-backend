package com.example.RealEstate.service;

import com.example.RealEstate.entity.MessageRequestEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.model.MessageRequestModel;
import com.example.RealEstate.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class MessageRequestService {

    private final MessageRepository messageRepository;

    public MessageRequestService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void buyerSendMessage(MessageRequestModel messageRequestModel) {
        MessageRequestEntity messageRequestEntity=new MessageRequestEntity();
        messageRequestEntity.setBuyerid(messageRequestModel.getBuyerId());
        messageRequestEntity.setSellerid(messageRequestModel.getSellerId());
        messageRequestEntity.setPropertyid(messageRequestModel.getPropertyId());
        messageRequestEntity.setMessage(messageRequestModel.getMessage());

        messageRequestEntity.setDate(new Date(System.currentTimeMillis()));

        messageRepository.save(messageRequestEntity);
    }
}
