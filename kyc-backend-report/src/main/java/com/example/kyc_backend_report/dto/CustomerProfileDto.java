package com.example.kyc_backend_report.dto;

public class CustomerProfileDto {
    private String selfie;

    public CustomerProfileDto(String selfie){
        this.selfie = selfie;
    }
    public String getSelfie(){
        return selfie;
    }
    
}
