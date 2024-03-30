package com.example.RealEstate.repository;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface SellerRepository extends JpaRepository<SellerEntity,Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    SellerEntity findByEmail(String email);
//    SellerEntity findByToken(String token);
    @Query("SELECT e FROM SellerEntity e WHERE e.username = :username AND e.password = :password")
    SellerEntity findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT COUNT(username) FROM SellerEntity e WHERE e.username = :username")
    int countByUsername(@Param("username") String username);
}
