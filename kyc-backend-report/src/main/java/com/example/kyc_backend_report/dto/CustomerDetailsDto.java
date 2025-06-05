package com.example.kyc_backend_report.dto;


public class CustomerDetailsDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
     private String dateOfBirth;
    private String employmentStatus;
    private String county;
    private String frontPhotoIdUrl;
    private String backPhotoIdUrl;
    private String selfieImageUrl;

    public CustomerDetailsDto( String firstName, String lastName,String email,String phoneNumber,  String dateIOfBirth, String employmentStatus, String county, String frontPhotoIdUrl, String backPhotIdUrl, String selfieImageUrl){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateIOfBirth;
        this.employmentStatus = employmentStatus;
        this.county = county;
        this.frontPhotoIdUrl = frontPhotoIdUrl;
        this.backPhotoIdUrl = backPhotIdUrl;
        this.selfieImageUrl = selfieImageUrl;
       

    }
    

     public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getCounty(){
        return county;
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
   

}
