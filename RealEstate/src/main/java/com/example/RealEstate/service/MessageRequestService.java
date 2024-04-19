package com.example.RealEstate.service;

import com.example.RealEstate.entity.MessageRequestEntity;
import com.example.RealEstate.entity.OrderEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.MessageRequestModel;
import com.example.RealEstate.model.OrderModel;
import com.example.RealEstate.repository.MessageRepository;
import com.example.RealEstate.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageRequestService {
@Autowired
    private final MessageRepository messageRepository;

@Autowired
private OrderRepository orderRepository;
    public MessageRequestService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void buyerSendMessage(MessageRequestModel messageRequestModel) {
        List<String> userError = new ArrayList<>();

        if (messageRequestModel.getBuyerId() == null || messageRequestModel.getBuyerId() ==0) {
            userError.add("BuyerId cannot be empty");
        }
        if (messageRequestModel.getSellerId() == null || messageRequestModel.getSellerId() ==0) {
            userError.add("SellerId cannot be empty");
        }
        if (messageRequestModel.getPropertyId() == null || messageRequestModel.getPropertyId() ==0) {
            userError.add("PropertyId cannot be empty");
        }
        if (messageRequestModel.getMessage() == null || messageRequestModel.getMessage().isEmpty()) {
            userError.add("Message cannot be empty");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        MessageRequestEntity messageRequestEntity=new MessageRequestEntity();
        messageRequestEntity.setBuyerid(messageRequestModel.getBuyerId());
        messageRequestEntity.setSellerid(messageRequestModel.getSellerId());
        messageRequestEntity.setPropertyid(messageRequestModel.getPropertyId());
        messageRequestEntity.setMessage(messageRequestModel.getMessage());

        messageRequestEntity.setDate(new Date(System.currentTimeMillis()));

        messageRepository.save(messageRequestEntity);
    }

//    public void placeOrder(OrderModel orderModel) {
//        OrderEntity orderEntity=new OrderEntity();
//        orderEntity.setBuyerid(orderModel.getBuyerId());
//        orderEntity.setSellerid(orderModel.getSellerId());
//        orderEntity.setPropertyid(orderModel.getPropertyId());
//        orderEntity.setPrice(orderModel.getPrice());
//orderRepository.save(orderEntity);
//    }
}
