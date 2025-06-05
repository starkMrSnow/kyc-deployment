package com.giktek.kyc_service.DTO;
import com.giktek.kyc_service.Entities.Customer;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long customerId;
    private String firstName = null;
    private String lastName = null;
    private String dateOfBirth = null;
    private String county = null;
    private String employmentStatus = null;
    private String phoneNumber = null;
    private String selfieImageUrl = null;
    private String frontPhotoIdUrl = null;
    private String backPhotoIdUrl = null;
    private String email = null;
    private boolean isCaptured = false;


    public CustomerDTO(Customer customer) {
    this.customerId = customer.getCustomerId();
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
    this.phoneNumber = customer.getPhoneNumber();
    this.dateOfBirth = customer.getDateOfBirth();
    this.county = customer.getCounty();
    this.employmentStatus = customer.getEmploymentStatus();
    this.selfieImageUrl = customer.getSelfieImageUrl();
    this.frontPhotoIdUrl = customer.getFrontPhotoIdUrl();
    this.backPhotoIdUrl = customer.getBackPhotoIdUrl();
    this.email = customer.getEmail();
    this.isCaptured = customer.isCaptured();
}

public Long getId(){
    return this.customerId;
}

}


