package com.giktek.kyc_service.Controllers;

import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.giktek.kyc_service.Entities.Customer;
// import com.giktek.kyc_service.Producer.RabbitMQProducer;
import com.giktek.kyc_service.Services.KYCService;

@RestController
@RequestMapping("/kyc")
@CrossOrigin("*")
public class KycController {

    KYCService kycService;

    public KycController(KYCService kycService) {
        this.kycService = kycService;
    }

    @PostMapping("/new-customer")
    public ResponseEntity<Map<String, Long>> newCustomer(@RequestBody Customer customer){
        System.out.println(customer.toString());
        return kycService.createCustomerAccount(customer);
    }


    @PutMapping("/upload-documents/{customerId}")
    public ResponseEntity<?> documentsUpload(
        @RequestParam ("frontPhotoId") MultipartFile frontPhotoId,
        @RequestParam ("backPhotoId") MultipartFile backPhotoId,
        @RequestParam ("selfieImage") MultipartFile selfieImage,
        @PathVariable Long customerId){
            System.out.println(customerId);
            return kycService.SaveCustomerImages(frontPhotoId, backPhotoId, selfieImage, customerId);
    }


    @PutMapping("/upload-email/{customerId}")
    public ResponseEntity<Map<String, Long>> emailUpload(
        @RequestParam String email,
        @PathVariable Long customerId){
            return kycService.saveCustomerEmail(email, customerId);
    }

    
     @GetMapping("/view-customer-details/{customerId}")
    public ResponseEntity<?> viewCustomerDetails(@PathVariable Long customerId){
        return kycService.selectCustomerDetails(customerId);
    }
}