package com.example.RealEstate.repository;

import com.example.RealEstate.entity.OrderEntity;
import com.example.RealEstate.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query("SELECT COUNT(o.buyerId) from OrderEntity o where o.buyerId=:buyerId")
    int findByBuyerId(@Param("buyerId") Long buyerId);
    @Query("SELECT COUNT(o.sellerId) from OrderEntity o where o.sellerId=:sellerId")
    int findBySellerId(@Param("sellerId") Long sellerId);
    @Query("SELECT COUNT(o.propertyId) from OrderEntity o where o.propertyId=:propertyId")
    int findByPropertyId(@Param("propertyId") Long propertyId);
    @Query("SELECT new com.example.RealEstate.model.OrderListingModel(o.buyerId,b.firstname,o.sellerId,s.firstname,o.propertyId,p.pic,p.city,o.price) " +
            "FROM OrderEntity o JOIN BuyerEntity b ON b.id = o.buyerId JOIN SellerEntity s ON s.id = o.sellerId JOIN PropertyEntity p " +
            "ON p.id = o.propertyId")
    List<OrderListingModel> getAllOrders();

    @Query("SELECT new com.example.RealEstate.model.OrderListByBuyerIdModel(b.firstname, b.lastname, b.address, b.email, b.phone, o.price) " +
            "FROM OrderEntity o JOIN BuyerEntity b ON b.id = o.buyerId " +
            "WHERE o.buyerId = :buyerId")
    List<OrderListByBuyerIdModel> getAllOrdersByBuyerId(@Param("buyerId") Long buyerId);


    @Query("SELECT new com.example.RealEstate.model.OrderListBySellerIdModel(b.firstname, b.lastname, b.address, b.email, b.phone, o.price) " +
            "FROM OrderEntity o JOIN BuyerEntity b ON b.id = o.buyerId " +
            "WHERE o.sellerId = :sellerId")
    List<OrderListBySellerIdModel> getAllOrdersBySellerId(@Param("sellerId") Long sellerId);


    @Query("SELECT new com.example.RealEstate.model.OrderListByPropertyIdModel(s.area,s.city,s.district,s.features,o.price) " +
            "FROM OrderEntity o JOIN PropertyEntity s ON s.id = o.propertyId"+
    " WHERE o.propertyId = :propertyId")
    List<OrderListByPropertyIdModel> getAllOrdersByPropertyId(@Param("propertyId") Long propertyId);


    List<OrderEntity> findByBuyerIdAndPropertyId(Long buyerId, Long propertyId);
}
