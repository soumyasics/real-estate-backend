package com.example.RealEstate.repository;

import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.model.PropertyListingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity,Long> {

    @Query("SELECT e FROM PropertyEntity e WHERE e.sid = :id ")
    List<PropertyEntity> findBySId(Long id);
    @Query("SELECT new com.example.RealEstate.model.PropertyListingModel(p.pic, p.lat, p.log, p.district, p.city, p.type, p.landmark, p.features, p.price, p.area, s.firstname, s.email) " +
            "FROM PropertyEntity p " +
            "JOIN SellerEntity s ON p.sid = s.id")
    List<PropertyListingModel> getAllProperties();


}







