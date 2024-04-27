package com.example.RealEstate.service;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.repository.BuyerRepository;
import com.example.RealEstate.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminService {
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    public List<BuyerEntity> getAllBuyers() {
        List<BuyerEntity> buyerEntityList=buyerRepository.findAll();
        return buyerEntityList;
    }
    public List<SellerEntity> getAllSellers() {
        List<SellerEntity> results = sellerRepository.findAll();
        return results;
    }

}
