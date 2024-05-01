package com.example.RealEstate.service;

import com.example.RealEstate.entity.*;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.*;
import com.example.RealEstate.repository.MessageRepository;
import com.example.RealEstate.repository.OrderRepository;
import com.example.RealEstate.repository.PropertyRepository;
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
    @Autowired
    private PropertyRepository propertyRepository;
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
        messageRequestEntity.setMessagefrom(messageRequestModel.getmessagefrom());
        messageRepository.save(messageRequestEntity);
    }

    public void placeOrder(OrderModel orderModel) {
        List<String> userError = new ArrayList<>();

        if (orderModel.getBuyerId() == null || orderModel.getBuyerId() ==0) {
            userError.add("BuyerId cannot be empty");
        }
        if (orderModel.getSellerId() == null || orderModel.getSellerId() ==0) {
            userError.add("SellerId cannot be empty");
        }
        if (orderModel.getPropertyId() == null || orderModel.getPropertyId() ==0) {
            userError.add("PropertyId cannot be empty");
        }
        if (orderModel.getPrice() == null || orderModel.getPrice()== 0) {
            userError.add("Price cannot be zero");
        }
        List<OrderEntity> existingOrders = orderRepository.findByBuyerIdAndPropertyId(orderModel.getBuyerId(), orderModel.getPropertyId());
        if (!existingOrders.isEmpty()) {
        userError.add("Order already exists for the same property by the same buyer");
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setBuyerId(orderModel.getBuyerId());
        orderEntity.setSellerId(orderModel.getSellerId());
        orderEntity.setPropertyId(orderModel.getPropertyId());
        orderEntity.setPrice(orderModel.getPrice());
        orderRepository.save(orderEntity);


        //status of the property to "soldout"
        Optional<PropertyEntity> propertyOptional = propertyRepository.findById(orderModel.getPropertyId());
        if (propertyOptional.isPresent()) {
            PropertyEntity propertyEntity = propertyOptional.get();

            propertyEntity.setStatus("soldout");
            propertyRepository.save(propertyEntity);
        }

    }

    public List<OrderListingModel> getAllOrders() {
        List<OrderListingModel> results = orderRepository.getAllOrders();
        return results;
    }

    public List<OrderListByBuyerIdModel> getorderListingByBuyerId(Long buyerId) {
        List<String> userError = new ArrayList<>();

        if (buyerId == null || buyerId ==0) {
            userError.add("BuyerId cannot be empty");
        }
        int count=orderRepository.findByBuyerId(buyerId);
        if(count==0){
            userError.add("BuyerId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        List<OrderListByBuyerIdModel> results = orderRepository.getAllOrdersByBuyerId(buyerId);
        return results;
    }
    public List<OrderListBySellerIdModel> getorderListingBySellerId(Long sellerId) {
        List<String> userError = new ArrayList<>();

        if (sellerId == null || sellerId ==0) {
            userError.add("SellerId cannot be empty");
        }
        int count=orderRepository.findBySellerId(sellerId);
        if(count==0){
            userError.add("SellerId not found");
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        List<OrderListBySellerIdModel> results = orderRepository.getAllOrdersBySellerId(sellerId);
        return results;
    }

    public List<OrderListByPropertyIdModel> getorderListingByPropertyId(Long propertyId) {
        List<String> userError = new ArrayList<>();

        if (propertyId == null || propertyId ==0) {
            userError.add("PropertyId cannot be empty");
        }
        int count=orderRepository.findByPropertyId(propertyId);
        if(count==0){
            userError.add("PropertyId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        List<OrderListByPropertyIdModel> results = orderRepository.getAllOrdersByPropertyId(propertyId);
        return results;
    }

    public PropertyViewModel getPropertyByPropertyId(Long id) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(id);
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntity = optionalPropertyEntity.get();
            PropertyViewModel propertyViewModel = new PropertyViewModel();
            propertyViewModel.setArea(propertyEntity.getArea());
            propertyViewModel.setCity(propertyEntity.getCity());
            propertyViewModel.setDistrict(propertyEntity.getDistrict());
            propertyViewModel.setFeatures(propertyEntity.getFeatures());
            propertyViewModel.setLandmark(propertyViewModel.getLandmark());
            propertyViewModel.setLat(propertyEntity.getLat());
            propertyViewModel.setLog(propertyViewModel.getLog());
            propertyViewModel.setPic(propertyViewModel.getPic());
            propertyViewModel.setPrice(propertyViewModel.getPrice());
            propertyViewModel.setSid(propertyViewModel.getSid());
            propertyViewModel.setType(propertyEntity.getType());

            return propertyViewModel;
        }

        return null;
    }

    public List<MessageListingByBuyerIdModel> getmessageListingByBuyerId(Long buyerId) {
        List<String> userError = new ArrayList<>();

        if (buyerId == null || buyerId ==0) {
            userError.add("BuyerId cannot be empty");
        }
        int count=messageRepository.findByBuyerId(buyerId);
        if(count==0){
            userError.add("BuyerId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        List<MessageListingByBuyerIdModel> results = messageRepository.getmessageListingByBuyerId(buyerId);
        return results;
    }

    public List<MessageListingByBuyerIdModel> getmessageListingBySellerId(Long sellerId) {
        List<String> userError = new ArrayList<>();

        if (sellerId == null || sellerId ==0) {
            userError.add("sellerId cannot be empty");
        }
        int count=messageRepository.findBySellerId(sellerId);
        if(count==0){
            userError.add("sellerId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        List<MessageListingByBuyerIdModel> results = messageRepository.getmessageListingBySellerId(sellerId);
        return results;
    }

    public List<MessageListingModel> getAllMessage() {
        List<MessageListingModel> results = messageRepository.getAllMessage();
        return results;
    }
}



