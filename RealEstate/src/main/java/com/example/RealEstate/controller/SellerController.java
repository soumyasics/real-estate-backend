package com.example.RealEstate.controller;

import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.BuyerLoginModel;
import com.example.RealEstate.model.PropertyModel;
import com.example.RealEstate.model.SellerLoginModel;
import com.example.RealEstate.model.SellerModel;
import com.example.RealEstate.service.SellerService;
import org.apache.velocity.exception.ResourceNotFoundException;
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

    @GetMapping("/sellerlogin")
    public ResponseEntity<?> sellerLogin(@Valid @RequestBody SellerLoginModel sellerLoginModel) {
        try {
            userService.sellerLogin(sellerLoginModel);
            return ResponseEntity.ok("Login Successfully");
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @PutMapping("/resetSeller-password")
    public String resetPass(@RequestParam String email, @RequestParam String password){
        return userService.resetPass(email,password);
    }

    @PostMapping("/AddProperty")
    public ResponseEntity<?> propertyRegistration(@Valid @ModelAttribute PropertyModel propertyModel, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("inside seller controller");
            PropertyEntity user1Entity=userService.saveproperty(propertyModel, file);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", user1Entity);
            return ResponseEntity.ok(response);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
    @PutMapping("/EditProperty{id}")
       public ResponseEntity<?> Editproperty(@PathVariable("id") Long id , @ModelAttribute PropertyModel propertyModel, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("inside seller controller");
            userService.updateProperty(id,propertyModel, file);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User Updated successfully");
            response.put("user", propertyModel);
            return ResponseEntity.ok(response);
        } catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @DeleteMapping("/DeleteProperty{id}")
    public ResponseEntity<String> deletePropertybyId(@PathVariable("id") Long id ) {

        userService.deletePropertybyId(id);
        return ResponseEntity.ok("Property deleted sucessfully!!!");
    }

}
