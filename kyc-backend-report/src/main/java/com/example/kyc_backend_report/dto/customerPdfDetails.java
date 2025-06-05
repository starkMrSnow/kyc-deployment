package com.example.kyc_backend_report.dto;

public class customerPdfDetails {
    private String firstName;
    private String employmentStatus;
    private String county;
    private String frontPhotoIdUrl;
    private String backPhotoIdUrl;
    private String selfieImageUrl;

     public customerPdfDetails(String firstName, String employmentStatus, String county, String frontPhotoIdUrl, String backPhotoIdUrl, String selfieImageUrl){
        this.firstName = firstName;
        this.employmentStatus = employmentStatus;
        this.county= county;
        this.frontPhotoIdUrl = frontPhotoIdUrl;
        this.backPhotoIdUrl = backPhotoIdUrl;
        this.selfieImageUrl = selfieImageUrl;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getEmploymentStatus(){
        return employmentStatus;
    }
    public String getCounty(){
        return county;
    }
    public String getFrontPhotoIdUrl(){
        return frontPhotoIdUrl;
    }
    public String getBackPhotoIdUrl(){
        return backPhotoIdUrl;
    }
    public String getSelfieImageUrl(){
        return selfieImageUrl;
    }
    
}
