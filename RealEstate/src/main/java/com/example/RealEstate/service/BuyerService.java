package com.example.RealEstate.service;

import com.example.RealEstate.entity.BuyerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;
import com.example.RealEstate.model.BuyerLoginModel;
import com.example.RealEstate.model.BuyerModel;
import com.example.RealEstate.model.BuyerProfileViewModel;
import com.example.RealEstate.model.BuyerUpdateModel;
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
import java.util.Optional;


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
        if (buyerModel.getAge()==0 || buyerModel.getAge()==null) {
            userError.add("Age cannot be empty");
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
        } else if (StringUtils.isEmpty(buyerModel.getPhone()) || String.valueOf(buyerModel.getPhone()).length() != 10) {
            userError.add("Phone number must contain 10 digits");
        }
        if (StringUtils.isEmpty(buyerModel.getUsername())) {
            userError.add("Username cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getAddress())) {
            userError.add("Address cannot be empty");
        }
        if (StringUtils.isEmpty(buyerModel.getPassword())) {
            userError.add("Password cannot be empty");
        }
        if (file == null || file.isEmpty()) {
            userError.add("File is required");
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

    public Long buyerLogin(BuyerLoginModel buyerLoginModel) {
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
            }else {
                return buyerEntity.getId();
            }
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        return null;
    }


    public void resetPassword(String email, String newPassword) {

        List<String> userError = new ArrayList<>();
        if (email == null || email.isEmpty()) {
            userError.add("Email cannot be empty");
        } else if (newPassword == null || newPassword.isEmpty()) {
            userError.add("Password cannot be empty");
        } else {
            int count = buyerRepository.countByEmail(email);
            if (count == 0) {
                userError.add("Email not found");
            } else {
                BuyerEntity buyerEntity = buyerRepository.findByEmail(email);
                if (buyerEntity != null) {
                    buyerEntity.setPassword(newPassword);
                    buyerRepository.save(buyerEntity);
                }
            }
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
    }

    public BuyerEntity updateBuyer(Long id,BuyerUpdateModel buyerUpdateModel, MultipartFile file) throws IOException {
        List<String> userError = new ArrayList<>();


        if (StringUtils.isEmpty(buyerUpdateModel.getFirstname())) {
            userError.add("Firstname cannot be empty");
        }
        if (StringUtils.isEmpty(buyerUpdateModel.getLastname())) {
            userError.add("Lastname cannot be empty");
        }
        if (StringUtils.isEmpty(buyerUpdateModel.getDob())) {
            userError.add("Dob cannot be empty");
        }
        if (StringUtils.isEmpty(buyerUpdateModel.getGender())) {
            userError.add("Gender cannot be empty");
        }
        String phoneNumber = String.valueOf(buyerUpdateModel.getPhone());
        if (!phoneNumber.matches("[0-9]+")) {
            userError.add("Phone number can only contain digits");
        } else if (StringUtils.isEmpty(buyerUpdateModel.getPhone()) || String.valueOf(buyerUpdateModel.getPhone()).length() != 10) {
            userError.add("Phone number must contain 10 digits");
        }

        if (StringUtils.isEmpty(buyerUpdateModel.getAddress())) {
            userError.add("Address cannot be empty");
        }
        if (StringUtils.isEmpty(buyerUpdateModel.getUsername())) {
            userError.add("Username cannot be empty");
        }
        if (StringUtils.isEmpty(buyerUpdateModel.getEmail())) {
            userError.add("Email cannot be empty");
        }
        if (file == null || file.isEmpty()) {
            userError.add("File is required");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }

        String fileName = file.getOriginalFilename();
        Path destination = Paths.get("C:\\Users\\ajeen\\OneDrive\\Desktop\\realestate\\real-estate-backend\\images", fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // Set the profile picture path in the user entity
        buyerUpdateModel.setProfile("images/" + fileName);

        BuyerEntity buyerEntity =buyerRepository.findById(id).orElseThrow(()-> new InputValidationFailedException("Id not exist",userError));
        buyerEntity.setFirstname(buyerUpdateModel.getFirstname());
        buyerEntity.setLastname(buyerUpdateModel.getLastname());
        buyerEntity.setAge(buyerUpdateModel.getAge());
        buyerEntity.setDob(buyerUpdateModel.getDob());
        buyerEntity.setGender(buyerUpdateModel.getGender());
        buyerEntity.setPhone(buyerUpdateModel.getPhone());
        buyerEntity.setEmail(buyerUpdateModel.getEmail());
        buyerEntity.setUsername(buyerUpdateModel.getUsername());
        buyerEntity.setAddress(buyerUpdateModel.getAddress());
        buyerEntity.setProfile(buyerUpdateModel.getProfile());
        return  buyerRepository.save(buyerEntity);
    }

    public BuyerProfileViewModel profileView(Long id) {
        List<String> userError = new ArrayList<>();
        int count=buyerRepository.countById(id);
        if(count==0){
            userError.add("BuyerId not found");
        }
        if(!userError.isEmpty()){
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        Optional<BuyerEntity> optionalBuyerEntity = buyerRepository.findById(id);
        if (optionalBuyerEntity.isPresent()) {
            BuyerEntity buyerEntity = optionalBuyerEntity.get();
            BuyerProfileViewModel buyerProfileViewModel = new BuyerProfileViewModel();
            buyerProfileViewModel.setFirstname(buyerEntity.getFirstname());
            buyerProfileViewModel.setLastname(buyerEntity.getLastname());
            buyerProfileViewModel.setAge(buyerEntity.getAge());
            buyerProfileViewModel.setDob(buyerEntity.getDob());
            buyerProfileViewModel.setGender(buyerEntity.getGender());
            buyerProfileViewModel.setPhone(buyerEntity.getPhone());
            buyerProfileViewModel.setEmail(buyerEntity.getEmail());
            buyerProfileViewModel.setAddress(buyerEntity.getAddress());
            buyerProfileViewModel.setUsername(buyerEntity.getUsername());
            buyerProfileViewModel.setPassword(buyerEntity.getPassword());
            buyerProfileViewModel.setProfile(buyerEntity.getProfile());

            return buyerProfileViewModel;
        }

        return null;
    }
    }









