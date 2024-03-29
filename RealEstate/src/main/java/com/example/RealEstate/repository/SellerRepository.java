package com.example.RealEstate.repository;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity,Long> {

  //  SellerEntity findByUsername(String username) ;


    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
