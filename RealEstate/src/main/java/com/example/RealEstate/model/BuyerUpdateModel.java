package com.example.RealEstate.model;

public class BuyerUpdateModel {
    private String firstname;
    private String lastname;
    private int age;
    private String dob;
    private String gender;
    private Long phone;
    private String email;
    private String address;
    private String username;
    private String profile;

    public BuyerUpdateModel() {
    }

    public BuyerUpdateModel(String firstname, String lastname, int age, String dob, String gender, Long phone, String email, String address, String username, String profile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.profile = profile;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public Long getId() {
        return null;
    }
}
