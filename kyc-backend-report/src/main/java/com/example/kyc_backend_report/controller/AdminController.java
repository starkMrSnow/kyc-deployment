package com.example.kyc_backend_report.controller;

import com.example.kyc_backend_report.dto.CustomerDetailsDto;
import com.example.kyc_backend_report.dto.CustomerDto;
import com.example.kyc_backend_report.dto.CustomerIdNameDto;
import com.example.kyc_backend_report.dto.CustomerProfileDto;
// import com.example.kyc_backend_report.dto.customerPdfDetails;
import com.example.kyc_backend_report.model.Customer;
import com.example.kyc_backend_report.repository.CustomerRepository;

import jakarta.transaction.Transactional;

import com.example.kyc_backend_report.config.PdfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;

//Research on this
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerRepository CustomerRepository;

    @Autowired
    private PdfService pdfService;
    
    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
      List<Customer> customers = CustomerRepository.findAll();

        return customers.stream().map(customer -> new CustomerDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getDateOfBirth(),
                customer.getCounty(),
                customer.getEmploymentStatus(), 
                customer.getSelfieImageUrl(),    
                customer.getFrontPhotoIdUrl(),
                customer.getBackPhotoIdUrl(),
                customer.getEmail(),
                customer.isCaptured()
        )).collect(Collectors.toList());
    }

    @GetMapping("/IdName")
    public List<CustomerIdNameDto> getCustomerIdsAndNames() {
        List<Customer> customers = CustomerRepository.findAllByOrderByCustomerIdAsc();
        return customers.stream()
                .map(customer -> new CustomerIdNameDto(customer.getCustomerId(), customer.getFirstName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/details")
    public List<CustomerDetailsDto> getCustomerDetails(){
        List<Customer> customers = CustomerRepository.findAll();
        return customers.stream().map(customer -> new CustomerDetailsDto(
          customer.getFirstName(),
          customer.getLastName(),
          customer.getEmail(),
          customer.getPhoneNumber(),
          customer.getDateOfBirth(),
          customer.getEmploymentStatus(),
         customer.getCounty(),
          customer.getFrontPhotoIdUrl(),
          customer.getBackPhotoIdUrl(),
          customer.getSelfieImageUrl()
          )).collect(Collectors.toList());
    }


    @GetMapping("/selfie")
    public List<CustomerProfileDto>getAllCustomerProfile(){
      List<Customer>customers = CustomerRepository.findAll();
      return customers.stream()
                .map(customer -> new CustomerProfileDto(customer.getSelfieImageUrl()))
                .collect(Collectors.toList());
    }

  

  @DeleteMapping("/rmSelfie/{email}")
 @Transactional
 public ResponseEntity<String> removeSelfie(@PathVariable String email){
    Customer customer = CustomerRepository.findByEmail(email);
    if (customer == null){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer with email " + email + " not found");
    }

    customer.setSelfieImageUrl(null);
    CustomerRepository.save(customer);

    return ResponseEntity.ok("Selfie image removed successfully");
}


    @CrossOrigin(origins = "http://localhost:4201")
    @GetMapping(value = "/export-pdf/{email}", produces = "application/pdf")
   public ResponseEntity<InputStreamResource> exportCustomerToPdf(@PathVariable String email) {
    Customer customer = CustomerRepository.findByEmail(email);
    if (customer == null) {
        return ResponseEntity.notFound().build();
    }

        CustomerDetailsDto dto = new CustomerDetailsDto(
          customer.getFirstName(),
          customer.getLastName(),
          customer.getEmail(),
          customer.getPhoneNumber(),         
          customer.getDateOfBirth(),
          customer.getEmploymentStatus(),
          customer.getCounty(),       
          customer.getFrontPhotoIdUrl(),
          customer.getBackPhotoIdUrl(),
          customer.getSelfieImageUrl()
        
        
    );

    ByteArrayInputStream bis = pdfService.generateCustomerPdf(dto);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=customer_details.pdf");

    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
}

}
    

  


