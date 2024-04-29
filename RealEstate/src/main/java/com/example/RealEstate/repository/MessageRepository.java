package com.example.RealEstate.repository;

import com.example.RealEstate.entity.MessageRequestEntity;
import com.example.RealEstate.model.MessageListingByBuyerIdModel;
import com.example.RealEstate.model.MessageListingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageRequestEntity,Long> {
    @Query("SELECT COUNT(m.buyerid) from MessageRequestEntity m where m.buyerid=:buyerId")
    int findByBuyerId(@Param("buyerId") Long buyerId);
    @Query("SELECT new com.example.RealEstate.model.MessageListingByBuyerIdModel(m.message, m.date, m.messagefrom) FROM MessageRequestEntity m WHERE m.buyerid = :buyerId AND m.messagefrom = 'buyer'")
    List<MessageListingByBuyerIdModel> getmessageListingByBuyerId(@Param("buyerId") Long buyerId);
    @Query("SELECT COUNT(m.sellerid) from MessageRequestEntity m where m.sellerid=:sellerId")
    int findBySellerId(@Param("sellerId")Long sellerId);
    @Query("SELECT new com.example.RealEstate.model.MessageListingByBuyerIdModel(m.message, m.date, m.messagefrom) FROM MessageRequestEntity m WHERE m.sellerid = :sellerId AND m.messagefrom = 'seller'")
    List<MessageListingByBuyerIdModel> getmessageListingBySellerId(@Param("sellerId") Long sellerId);
    @Query("SELECT new com.example.RealEstate.model.MessageListingModel(m.buyerid,m.sellerid,m.propertyid,m.date,m.message,m.messagefrom) " +
            "FROM MessageRequestEntity m ")
    List<MessageListingModel> getAllMessage();
}
