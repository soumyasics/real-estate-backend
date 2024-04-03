package com.example.RealEstate.repository;

import com.example.RealEstate.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<BuyerEntity,Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT e FROM BuyerEntity e WHERE e.username = :username AND e.password = :password")
    BuyerEntity findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT COUNT(username) FROM BuyerEntity e WHERE e.username = :username")
    int countByUsername(@Param("username") String username);
    @Query("SELECT COUNT(email) FROM BuyerEntity e WHERE e.email = :email")
    int countByEmail(@Param("email") String email);
    BuyerEntity findByEmail(String email);
}