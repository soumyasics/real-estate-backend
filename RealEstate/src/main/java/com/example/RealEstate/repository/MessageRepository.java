package com.example.RealEstate.repository;

import com.example.RealEstate.entity.MessageRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageRequestEntity,Long> {

}
