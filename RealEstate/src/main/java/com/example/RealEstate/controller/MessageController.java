package com.example.RealEstate.controller;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.MessageRequestModel;
import com.example.RealEstate.model.OrderModel;
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
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/message")
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
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok("Ordered successfully");
    }
}


