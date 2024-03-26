package com.example.RealEstate.controller;

import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.BuyerModel;
import com.example.RealEstate.service.BuyerService;
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
@RequestMapping("/user")
public class BuyerController {
    @Autowired
    private BuyerService userService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid @ModelAttribute BuyerModel buyerModel, @RequestParam("file") MultipartFile file) {
        try {
            userService.saveUser(buyerModel, file);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", buyerModel);
            return ResponseEntity.ok(response);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


}
