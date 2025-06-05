package com.example.kyc_backend_report.dto;

public class CustomerIdNameDto {
    private  Long customerId;
    private String firstName;

    public CustomerIdNameDto(Long customerId, String firstName){
        this.customerId = customerId;
        this.firstName = firstName;
    }
    public Long getCustomerId(){
        return customerId;
    }
    public String getFirstName(){
        return firstName;
    }
}
