package com.example.RealEstate.repository;

import com.example.RealEstate.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


public interface PropertyRepository extends JpaRepository<PropertyEntity,Long> {

    @Query("SELECT e FROM PropertyEntity e WHERE e.sid = :id ")
    List<PropertyEntity> findByPId(Long id);


}
