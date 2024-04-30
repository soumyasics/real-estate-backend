package com.example.RealEstate.controller;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.*;
import com.example.RealEstate.repository.BuyerRepository;
import com.example.RealEstate.repository.PropertyRepository;
import com.example.RealEstate.repository.SellerRepository;
import com.example.RealEstate.service.BuyerService;
import com.example.RealEstate.service.MessageRequestService;
import com.example.RealEstate.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {


    @Autowired
    private MessageRequestService messageRequestService;
    @PostMapping()
    public ResponseEntity<?> buyerSendMessage(@RequestBody MessageRequestModel messageRequestModel) {
        try {
            messageRequestService.buyerSendMessage(messageRequestModel);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok("Message sent successfully");
    }
    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderModel orderModel) {
        try {
            messageRequestService.placeOrder(orderModel);
            return ResponseEntity.ok("Ordered successfully");
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @GetMapping("/orderListing")
    public ResponseEntity<List<OrderListingModel>> orderListing() {
        List<OrderListingModel> orderListing = messageRequestService.getAllOrders();
        return ResponseEntity.ok(orderListing);
    }
    @GetMapping("/orderListingByBuyerId/{buyerId}")
    public ResponseEntity<?> orderListingByBuyerId(@PathVariable("buyerId") Long buyerId) {
        try {
            List<OrderListByBuyerIdModel> orderListingByBuyerId = messageRequestService.getorderListingByBuyerId(buyerId);
            return ResponseEntity.ok(orderListingByBuyerId);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @GetMapping("/orderListingBySellerId/{sellerId}")
    public ResponseEntity<?> orderListingBySellerId(@PathVariable("sellerId") Long sellerId){
        try {
            List<OrderListBySellerIdModel> orderListingBySellerId = messageRequestService.getorderListingBySellerId(sellerId);
            return ResponseEntity.ok(orderListingBySellerId);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @GetMapping("/orderListingByPropertyId/{propertyId}")
    public ResponseEntity<?> orderListingByPropertyId(@PathVariable("propertyId") Long propertyId){
        try {
            List<OrderListByPropertyIdModel> orderListingByPropertyId = messageRequestService.getorderListingByPropertyId(propertyId);
            return ResponseEntity.ok(orderListingByPropertyId);
        }catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @GetMapping("/getPropertyByPropertyId/{id}")
    public ResponseEntity<?> getPropertyByPropertyId(@PathVariable("id") Long id) {
        try {
            PropertyViewModel propertyViewModel = messageRequestService.getPropertyByPropertyId(id);
            return ResponseEntity.ok(propertyViewModel);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @GetMapping("/messageListing")
    public ResponseEntity<List<MessageListingModel>> messageListing() {
        List<MessageListingModel> messageListing = messageRequestService.getAllMessage();
        return ResponseEntity.ok(messageListing);
    }
    @GetMapping("/messageListingByBuyerId/{buyerId}")
    public ResponseEntity<?> messageListingByBuyerId(@PathVariable("buyerId") Long buyerId) {
        try {
            List<MessageListingByBuyerIdModel> messageListingByBuyerId = messageRequestService.getmessageListingByBuyerId(buyerId);
            return ResponseEntity.ok(messageListingByBuyerId);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @GetMapping("/messageListingBySellerId/{sellerId}")
    public ResponseEntity<?> messageListingBySellerId(@PathVariable("sellerId") Long sellerId) {
        try {
            List<MessageListingByBuyerIdModel> messageListingBySellerId = messageRequestService.getmessageListingBySellerId(sellerId);
            return ResponseEntity.ok(messageListingBySellerId);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}



