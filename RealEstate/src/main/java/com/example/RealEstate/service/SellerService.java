package com.example.RealEstate.service;



import com.example.RealEstate.entity.PropertyEntity;
import com.example.RealEstate.entity.SellerEntity;
import com.example.RealEstate.exception.InputValidationFailedException;

import com.example.RealEstate.model.*;

import com.example.RealEstate.repository.BuyerRepository;
import com.example.RealEstate.repository.PropertyRepository;
import com.example.RealEstate.repository.SellerRepository;

import org.apache.velocity.exception.ResourceNotFoundException;
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
public class SellerService {

    @Autowired
    private SellerRepository userRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    public Long sellerLogin(SellerLoginModel sellerLoginModel) {
        List<String> userError = new ArrayList<>();
        if (sellerLoginModel.getUsername() == null || sellerLoginModel.getUsername().isEmpty()) {
            userError.add("Username cannot be empty");
        }
        if (sellerLoginModel.getPassword() == null || sellerLoginModel.getPassword().isEmpty()) {
            userError.add("Password cannot be empty");
        }

        int count = userRepository.countByUsername(sellerLoginModel.getUsername());
        if (count == 0) {
            userError.add("Username does not exist");
        } else {
            SellerEntity sellerEntity = userRepository.findByUsernameAndPassword(sellerLoginModel.getUsername(), sellerLoginModel.getPassword());
            if (sellerEntity == null) {
                userError.add("Invalid password");
            } else {
                return sellerEntity.getId();
            }
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        return null;
    }


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
        } else if (StringUtils.isEmpty(sellerModel.getPhone()) || String.valueOf(sellerModel.getPhone()).length() != 10) {
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


// Get the current working directory path
        String currentPath = Paths.get("").toAbsolutePath().toString();


        File folder = new File(currentPath + "/images");
        if (!folder.exists()) {
            folder.mkdirs();
        }


        // Save the image file to folder
        String fileName = file.getOriginalFilename();
        Path destination = Paths.get(currentPath + "/images", fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        // Set the profile picture path in the user entity
        sellerModel.setProfile("images/" + fileName);

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

    private static PropertyEntity getPropertyEntity(PropertyModel propertyModel) {
        PropertyEntity userEntity = new PropertyEntity();
        userEntity.setSid(propertyModel.getSid());
        userEntity.setArea(propertyModel.getArea());
        userEntity.setCity(propertyModel.getCity());
        userEntity.setDistrict(propertyModel.getDistrict());
        userEntity.setFeatures(propertyModel.getFeatures());
        userEntity.setPrice(propertyModel.getPrice());
        userEntity.setLandmark(propertyModel.getLandmark());
        userEntity.setLat(propertyModel.getLat());
        userEntity.setLog(propertyModel.getLog());
        userEntity.setType(propertyModel.getType());
        userEntity.setIsActive(1);
        userEntity.setPic(propertyModel.getPic());
        return userEntity;
    }

    public static PropertyModel getPropertyModel(PropertyEntity propertyentity) {
        PropertyModel userEntity = new PropertyModel();
        userEntity.setSid(propertyentity.getSid());
        userEntity.setArea(propertyentity.getArea());
        userEntity.setCity(propertyentity.getCity());
        userEntity.setDistrict(propertyentity.getDistrict());
        userEntity.setFeatures(propertyentity.getFeatures());
        userEntity.setPrice(propertyentity.getPrice());
        userEntity.setLandmark(propertyentity.getLandmark());
        userEntity.setLat(propertyentity.getLat());
        userEntity.setLog(propertyentity.getLog());
        userEntity.setType(propertyentity.getType());
        userEntity.setIsActive(propertyentity.getIsActive());
        userEntity.setPic(propertyentity.getPic());
        return userEntity;
    }

    public String resetPass(String email, String password) {

        if (StringUtils.isEmpty(password)) {
            return "Password cannot be empty";
        }

        String email1 = String.valueOf(userRepository.findByEmail(email));
        Optional<SellerEntity> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if (email1.equals(null)) {
            return "Invalid email";
        } else {

            SellerEntity user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
        }
        return "Your password successfully updated.";
    }


    public SellerEntity updateSeller(Long id, SellerUpdateModel sellerUpdateModel) throws IOException {
        List<String> userError = new ArrayList<>();


        if (StringUtils.isEmpty(sellerUpdateModel.getFirstname())) {
            userError.add("Firstname cannot be empty");
        }
        if (StringUtils.isEmpty(sellerUpdateModel.getLastname())) {
            userError.add("Lastname cannot be empty");
        }
        if (StringUtils.isEmpty(sellerUpdateModel.getDob())) {
            userError.add("Dob cannot be empty");
        }
        if (StringUtils.isEmpty(sellerUpdateModel.getGender())) {
            userError.add("Gender cannot be empty");
        }
        String phoneNumber = String.valueOf(sellerUpdateModel.getPhone());
        if (!phoneNumber.matches("[0-9]+")) {
            userError.add("Phone number can only contain digits");
        } else if (StringUtils.isEmpty(sellerUpdateModel.getPhone()) || String.valueOf(sellerUpdateModel.getPhone()).length() != 10) {
            userError.add("Phone number must contain 10 digits");
        }

        if (StringUtils.isEmpty(sellerUpdateModel.getAddress())) {
            userError.add("Address cannot be empty");
        }
        if (StringUtils.isEmpty(sellerUpdateModel.getUsername())) {
            userError.add("Username cannot be empty");
        }
        if (StringUtils.isEmpty(sellerUpdateModel.getEmail())) {
            userError.add("Email cannot be empty");
        }

        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }


        SellerEntity sellerEntity =userRepository.findById(id).orElseThrow(()-> new InputValidationFailedException("Id not exist",userError));
        sellerEntity.setFirstname(sellerUpdateModel.getFirstname());
        sellerEntity.setLastname(sellerUpdateModel.getLastname());
        sellerEntity.setAge(sellerUpdateModel.getAge());
        sellerEntity.setDob(sellerUpdateModel.getDob());
        sellerEntity.setGender(sellerUpdateModel.getGender());
        sellerEntity.setPhone(sellerUpdateModel.getPhone());
        sellerEntity.setEmail(sellerUpdateModel.getEmail());
        sellerEntity.setUsername(sellerUpdateModel.getUsername());
        sellerEntity.setAddress(sellerUpdateModel.getAddress());

        return  userRepository.save(sellerEntity);
    }

    public PropertyEntity saveproperty(PropertyModel propertyModel, MultipartFile file) throws IOException {
        List<String> userError = new ArrayList<>();

        if (StringUtils.isEmpty(propertyModel.getArea())) {
            userError.add("Area cannot be empty");
        }
        if (StringUtils.isEmpty(propertyModel.getCity())) {
            userError.add("City cannot be empty");
        }
        if (StringUtils.isEmpty(propertyModel.getDistrict())) {
            userError.add("District cannot be empty");
        }
        if (StringUtils.isEmpty(propertyModel.getFeatures())) {
            userError.add("Features cannot be empty");
        }
        if (StringUtils.isEmpty(propertyModel.getPrice())) {
            userError.add("Price cannot be empty");
        }


        // Get the current working directory path
        String currentPath = Paths.get("").toAbsolutePath().toString();

        // Create a folder
        File folder = new File(currentPath + "/images");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Save the image file to folder
        String fileName = file.getOriginalFilename();
        Path destination = Paths.get(currentPath + "/images", fileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // Set the profile picture path in the user entity
        propertyModel.setPic("images/" + fileName);

        PropertyEntity user1Entity = getPropertyEntity(propertyModel);

        propertyRepository.save(user1Entity);
        return user1Entity;

    }

    public PropertyEntity updateProperty(Long id, PropertyModel propertyModel, MultipartFile file) {
        PropertyEntity property = propertyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not found:" + id));
        property.setArea(propertyModel.getArea());
        property.setCity(propertyModel.getCity());
        property.setDistrict(propertyModel.getDistrict());
        property.setFeatures(propertyModel.getFeatures());
        property.setPrice(propertyModel.getPrice());
        property.setSid(propertyModel.getSid());
        property.setLandmark(propertyModel.getLandmark());
        property.setLat(propertyModel.getLat());
        property.setLog(propertyModel.getLog());
        property.setType(propertyModel.getType());
        property.setIsActive(1);
        property.setPic(propertyModel.getPic());
        PropertyEntity updatedpropertyobj = propertyRepository.save(property);
        return updatedpropertyobj;
    }

    public void deletePropertybyId(Long id) {
        PropertyEntity propertyEntity = propertyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property is not found:" + id));
        propertyRepository.deleteById(id);
    }


    public List<PropertyListingModel> getAllProperties() {
        List<PropertyListingModel> results = propertyRepository.getAllProperties();
        return results;
    }


    public List<PropertyEntity> getPropertybySId(Long sid) {
        List<PropertyEntity> properties = propertyRepository.findBySId(sid);

        return properties;
    }

    public Long getSellerId(Long sellerid) {
        return sellerid;
    }

    public Long getPropertyId(Long id) {
        return id;
    }

    public SellerProfileViewModel profileView(Long id) {
        List<String> userError = new ArrayList<>();
        int count = userRepository.countById(id);
        if (count == 0) {
            userError.add("SellerId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        Optional<SellerEntity> optionalSellerEntity = userRepository.findById(id);
        if (optionalSellerEntity.isPresent()) {
            SellerEntity sellerEntity = optionalSellerEntity.get();
            SellerProfileViewModel sellerProfileViewModel = new SellerProfileViewModel();
            sellerProfileViewModel.setFirstname(sellerEntity.getFirstname());
            sellerProfileViewModel.setLastname(sellerEntity.getLastname());
            sellerProfileViewModel.setAge(sellerEntity.getAge());
            sellerProfileViewModel.setDob(sellerEntity.getDob());
            sellerProfileViewModel.setGender(sellerEntity.getGender());
            sellerProfileViewModel.setPhone(sellerEntity.getPhone());
            sellerProfileViewModel.setEmail(sellerEntity.getEmail());
            sellerProfileViewModel.setAddress(sellerEntity.getAddress());
            sellerProfileViewModel.setUsername(sellerEntity.getUsername());
            sellerProfileViewModel.setPassword(sellerEntity.getPassword());
            sellerProfileViewModel.setProfile(sellerEntity.getProfile());

            return sellerProfileViewModel;
        }

        return null;
    }

    public List<ViewAllPropertiesModel> viewAllProperties() {
        List<ViewAllPropertiesModel> results = propertyRepository.viewAllProperties();
        return results;
    }

    public PropertyAndSellerModel getPropertyById(Long id) {
        List<String> userError = new ArrayList<>();
        int count=propertyRepository.countById(id);
        if(count == 0){
            userError.add("PropertyId not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidationFailedException("Input validation failed", userError);
        }
        PropertyAndSellerModel property = propertyRepository.getPropertyAndSeller(id);

        return property;
    }
}


