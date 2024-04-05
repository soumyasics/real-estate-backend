package com.example.RealEstate.repository;

import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.model.PropertyAndSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class PropertyAndSellerRepository extends JpaRepository<PropertyAndSeller,Long> {
    @Query("SELECT e FROM PropertyEntity e WHERE e.id = :id ")
    PropertyAndSeller findByPId(Long id);
}
