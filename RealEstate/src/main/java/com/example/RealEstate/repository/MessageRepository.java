package com.example.RealEstate.repository;

import com.example.RealEstate.entity.MessageRequestEntity;
import com.example.RealEstate.model.MessageListingByBuyerIdModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageRequestEntity,Long> {
//@Query("SELECT new com.example.RealEstate.model.MessageListingByBuyerIdModel(m.message,m.date,m.messagefrom FROM MessageRequestEntity m WHERE m.buyerId=:buyerId)")
//    List<MessageListingByBuyerIdModel> getmessageListingByBuyerId(@Param("buyerId") Long buyerId);
}
