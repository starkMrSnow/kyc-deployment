package com.example.kyc_backend_report.dto;

public class CustomerDto {


    private Long customerId;
    private String firstName;
    private String lastName;
     private String phoneNumber;
    private String dateOfBirth;
    private String county;
    private String employmentStatus;
    private String selfieImageUrl;
    private String frontPhotoIdUrl;
    private String backPhotoIdUrl;
    private String email;
    private boolean isCaptured = false;


    // Constructors
    
    public CustomerDto(Long customerId, String firstName, String lastName,  String phoneNumber, String dateOfBirth,  String county, String employmentStatus, String selfieImageUrl, String frontPhotoIdUrl, String backPhotoIdUrl, String email, boolean isCaptured ) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.county = county;
        this.employmentStatus = employmentStatus;
        this.selfieImageUrl = selfieImageUrl;
        this.frontPhotoIdUrl = frontPhotoIdUrl;
        this.backPhotoIdUrl = backPhotoIdUrl;
        this.email = email;
        this.isCaptured = isCaptured = false;
    }   

   //Getters
     public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getCounty(){
        return county;
    }
    public Long getCustomerId(){
        return customerId;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmploymentStatus(){
        return employmentStatus;
    }
    public String getBackPhotoIdUrl(){
        return backPhotoIdUrl;
    }
    public String getFrontPhotoIdUrl(){
        return frontPhotoIdUrl;
    }
    public String getSelfieImageUrl(){
        return selfieImageUrl;
    }
    public boolean getIsCaptured(){
        return isCaptured;
    }

}
