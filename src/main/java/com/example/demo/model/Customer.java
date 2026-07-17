package com.example.demo.model;

public class Customer {

    private String customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String panNumber;
    private String email;
    private String mobileNumber;
    private String address;

    // Default Constructor
    public Customer() {
    }

    // Parameterized Constructor
    public Customer(String customerId,
                    String firstName,
                    String lastName,
                    Integer age,
                    String panNumber,
                    String email,
                    String mobileNumber,
                    String address) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.panNumber = panNumber;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    // Getters and Setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}