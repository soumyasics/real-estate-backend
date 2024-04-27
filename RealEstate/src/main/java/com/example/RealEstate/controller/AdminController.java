package com.example.RealEstate.controller;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Check if the username and password match the dummy values
        if ("admin".equals(username) && "password".equals(password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/getAllBuyers")
    public ResponseEntity<List<BuyerEntity>> getAllBuyers(){
        List<BuyerEntity> buyerEntityList=adminService.getAllBuyers();
        return ResponseEntity.ok(buyerEntityList);
    }
    @GetMapping("/sellerListing")
    public ResponseEntity<List<SellerEntity>> sellerListing(){
        List<SellerEntity> sellers= adminService.getAllSellers();
        return ResponseEntity.ok(sellers);

    }
}
