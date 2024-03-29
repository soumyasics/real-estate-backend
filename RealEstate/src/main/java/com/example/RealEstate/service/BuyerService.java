package com.example.RealEstate.service;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.BuyerLoginModel;
import com.example.RealEstate.model.BuyerModel;
import com.example.RealEstate.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    public void saveUser(BuyerModel buyerModel, MultipartFile file) throws IOException {
        List<String> userError = new ArrayList<>();

       
        if (buyerRepository.existsByEmail(buyerModel.getEmail())) {
            userError.add("Email already exists");
        }
        if (buyerRepository.existsByUsername(buyerModel.getUsername())) {
            userError.add("Username already exists");
        }
        if (StringUtils.isEmpty(buyerModel.getFirstname())) {
            userError.add("Firstname cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getLastname())) {
            userError.add("Lastname cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getDob())) {
            userError.add("Dob cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getGender())) {
            userError.add("Gender cannot be empty");
        }
        String phoneNumber = String.valueOf(buyerModel.getPhone());
        if (!phoneNumber.matches("[0-9]+")) {
            userError.add("Phone number can only contain digits");
        }

        else if (StringUtils.isEmpty(buyerModel.getPhone()) || String.valueOf(buyerModel.getPhone()).length() != 10) {
            userError.add("Phone number must contain 10 digits");
        }

        if (StringUtils.isEmpty(buyerModel.getAddress())) {
            userError.add("Address cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getPassword())) {
            userError.add("Password cannot be empty");
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }


        // Create a folder
            File folder = new File("E:\\Estate\\real-estate-backend\\images");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Save the image file to folder
            String fileName = file.getOriginalFilename();
            Path destination = Paths.get("E:\\Estate\\real-estate-backend\\images", fileName);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            // Set the profile picture path in the user entity
        buyerModel.setProfile("images/" + fileName);

        BuyerEntity userEntity = getUserEntity(buyerModel);


            buyerRepository.save(userEntity);


        }

    private static BuyerEntity getUserEntity(BuyerModel buyerModel) {
        BuyerEntity userEntity = new BuyerEntity();
        userEntity.setFirstname(buyerModel.getFirstname());
        userEntity.setLastname(buyerModel.getLastname());
        userEntity.setAge(buyerModel.getAge());
        userEntity.setDob(buyerModel.getDob());
        userEntity.setGender(buyerModel.getGender());
        userEntity.setPhone(buyerModel.getPhone());
        userEntity.setEmail(buyerModel.getEmail());
        userEntity.setAddress(buyerModel.getAddress());
        userEntity.setUsername(buyerModel.getUsername());
        userEntity.setPassword(buyerModel.getPassword());
        userEntity.setProfile(buyerModel.getProfile());

        return userEntity;
    }

    public void buyerLogin(BuyerLoginModel buyerLoginModel) {
        List<String> userError = new ArrayList<>();
        if (buyerLoginModel.getUsername() == null || buyerLoginModel.getUsername().isEmpty()) {
            userError.add("Username cannot be empty");
        }
        if (buyerLoginModel.getPassword() == null || buyerLoginModel.getPassword().isEmpty()) {
            userError.add("Password cannot be empty");
        }

        int count = buyerRepository.countByUsername(buyerLoginModel.getUsername());
        if (count == 0) {
            userError.add("Username does not exist");
        } else {
            BuyerEntity buyerEntity = buyerRepository.findByUsernameAndPassword(buyerLoginModel.getUsername(), buyerLoginModel.getPassword());
            if (buyerEntity == null) {
                userError.add("Invalid password");
            }
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
    }




}

