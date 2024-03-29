package com.example.RealEstate.controller;

import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.SellerModel;
import com.example.RealEstate.service.BuyerService;
import com.example.RealEstate.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Seller")
public class SellerController {
    @Autowired
    private SellerService userService;

    @PostMapping("/sellerRegister")
    public ResponseEntity<?> userRegistration(@Valid @ModelAttribute SellerModel sellerModel, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("inside seller controller");
            userService.saveUser(sellerModel, file);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", sellerModel);
            return ResponseEntity.ok(response);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    @GetMapping("/test")
    public ResponseEntity<String> getSampString(){

        return ResponseEntity.ok("Hello  printed");

    }
    
//    @GetMapping("/Login")
//    public ResponseEntity<?> getEmployeeId(@RequestParam("username") String username,@RequestParam("password") String password) {
//        try {
//            SellerModel savedSeller = SellerService.getSellerByUname(username, password);
//            return ResponseEntity.ok(savedSeller);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//
//    }


}
