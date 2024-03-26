package com.example.RealEstate.repository;

import com.example.RealEstate.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<BuyerEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
