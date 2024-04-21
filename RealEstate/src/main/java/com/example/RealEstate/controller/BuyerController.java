package com.example.RealEstate.controller;

import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.BuyerLoginModel;
import com.example.RealEstate.model.BuyerModel;
import com.example.RealEstate.model.BuyerUpdateModel;
import com.example.RealEstate.model.SellerModel;
import com.example.RealEstate.service.BuyerService;
import com.example.RealEstate.service.SellerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@CrossOrigin(origins = "http://localhost:3000")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/register")
    public ResponseEntity<?> buyerRegistration(@Valid @ModelAttribute BuyerModel buyerModel,@RequestParam MultipartFile file) {
        try {
            buyerService.saveUser(buyerModel,file);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", buyerModel);
            return ResponseEntity.ok(response);
        } catch (InputValidationFailedException e) {


            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
@PostMapping("/buyerlogin")
    public ResponseEntity<?> buyerLogin(@Valid @RequestBody BuyerLoginModel buyerLoginModel) {
    try {
        buyerService.buyerLogin(buyerLoginModel);
        return ResponseEntity.ok("Login Successfully");
    } catch (InputValidationFailedException e) {
        List<String> errors = e.getErrors();
        String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}

@PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam String email,@RequestParam String newPassword){
        try{
            buyerService.resetPassword(email,newPassword);
            return ResponseEntity.ok("Password reset Successfully");
        }catch (InputValidationFailedException e) {
            List<String> errors = e.getErrors();
            String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
}
@PutMapping("/updateBuyer/{id}")
    public ResponseEntity<String> updateBuyer(@PathVariable("id") Long id,@Valid @ModelAttribute BuyerUpdateModel buyerUpdateModel,@RequestParam("file") MultipartFile file) {
    try {
        buyerService.updateBuyer(id,buyerUpdateModel,file);
        return ResponseEntity.ok("update successfully");
    } catch (InputValidationFailedException e) {
        List<String> errors = e.getErrors();
        String errorMessage = "{ error: { message: \"" + String.join(",", errors) + "\" } }";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logout successful");
    }

}
