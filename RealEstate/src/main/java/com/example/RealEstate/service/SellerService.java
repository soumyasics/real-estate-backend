package com.example.RealEstate.service;

import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.SellerModel;
import com.example.RealEstate.repository.SellerRepository;
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
public class SellerService {

    @Autowired
    private SellerRepository userRepository;


//    public  SellerEntity getSellerByUname(String username, String password) {
//        SellerEntity seller=SellerRepository.findByUsername(username)
//                .orElseThrow(()->
//                        new ResourceNotFoundException("Employee is not found:"+username));
//
//        return EmployeeMapper.maptoEmployeedto(employee);
//    }



    public void saveUser(SellerModel sellerModel, MultipartFile file) throws IOException {
        List<String> userError = new ArrayList<>();
        if (userRepository.existsByEmail(sellerModel.getEmail())) {
            userError.add("Email already exists");
        }
        if (userRepository.existsByUsername(sellerModel.getUsername())) {
            userError.add("Username already exists");
        }
        if (StringUtils.isEmpty(sellerModel.getFirstname())) {
            userError.add("Firstname cannot be empty");
        }
        if (StringUtils.isEmpty(sellerModel.getLastname())) {
            userError.add("Lastname cannot be empty");
        }
        if (StringUtils.isEmpty(sellerModel.getDob())) {
            userError.add("Dob cannot be empty");
        }
        if (StringUtils.isEmpty(sellerModel.getGender())) {
            userError.add("Gender cannot be empty");
        }
        String phoneNumber = String.valueOf(sellerModel.getPhone());
        if (!phoneNumber.matches("[0-9]+")) {
            userError.add("Phone number can only contain digits");
        }

        else if (StringUtils.isEmpty(sellerModel.getPhone()) || String.valueOf(sellerModel.getPhone()).length() != 10) {
            userError.add("Phone number must contain 10 digits");
        }

        if (StringUtils.isEmpty(sellerModel.getAddress())) {
            userError.add("Address cannot be empty");
        }
        if (StringUtils.isEmpty(sellerModel.getPassword())) {
            userError.add("Password cannot be empty");
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }

       
//     sellerRegister

        // Create a folder
            File folder = new File("E:\\test1\\real-estate-backend\\sellerproimages");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Save the image file to folder
            String fileName = file.getOriginalFilename();
            Path destination = Paths.get("E:\\test1\\real-estate-backend\\sellerproimages", fileName);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            // Set the profile picture path in the user entity
        sellerModel.setProfile("sellerproimages/" + fileName);

        SellerEntity userEntity = getUserEntity(sellerModel);

            userRepository.save(userEntity);


        }

    private static SellerEntity getUserEntity(SellerModel sellerModel) {
        SellerEntity userEntity = new SellerEntity();
        userEntity.setFirstname(sellerModel.getFirstname());
        userEntity.setLastname(sellerModel.getLastname());
        userEntity.setAge(sellerModel.getAge());
        userEntity.setDob(sellerModel.getDob());
        userEntity.setGender(sellerModel.getGender());
        userEntity.setPhone(sellerModel.getPhone());
        userEntity.setEmail(sellerModel.getEmail());
        userEntity.setAddress(sellerModel.getAddress());
        userEntity.setUsername(sellerModel.getUsername());
        userEntity.setPassword(sellerModel.getPassword());
        userEntity.setProfile(sellerModel.getProfile());

        return userEntity;
    }
}

