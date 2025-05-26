package com.giktek.kyc_service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.giktek.kyc_service.Entities.Customer;
import com.giktek.kyc_service.DTO.CustomerDTO;
import com.giktek.kyc_service.Repositories.CustomerRepo;
import java.io.IOException;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.*;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
// import com.giktek.onboarding.Models.CustomerDTO;


@Service
public class KYCService {
    
//  Inject CustomerRepo to KYCService
    @Autowired
    private CustomerRepo repo;

//  Create new customer and add personal details    
    public ResponseEntity<Customer> createCustomerAccount(Customer customer){
        try{
        repo.save(customer);
        }
        catch(DataIntegrityViolationException e){
            return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    


//  Update customer details to include National ID and profile image
    public ResponseEntity<?> SaveCustomerImages(MultipartFile frontPhotoId, MultipartFile backPhotoId, MultipartFile selfieImage, Long customerId){
    //  Select customer        
        Customer customer = new Customer();
        customer = repo.getReferenceById(customerId);

    //  Set customer's URLs
        customer.setSelfieImageUrl(getImageUrl(selfieImage));
        customer.setFrontPhotoIdUrl(getImageUrl(frontPhotoId));
        customer.setBackPhotoIdUrl(getImageUrl(backPhotoId));

    //  Save customer's updated details
        customer = repo.save(customer);

        CustomerDTO dto = new CustomerDTO(customer);

    //  Return response
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }


//  Save image to cloud and return reference URL    
    public String getImageUrl(MultipartFile image){
    //  Get Cloudinary env variables
        String CLOUDINARY_URL = "cloudinary://433822546358674:h1eFzKNbuPcPwHApiwnwIBNeKY4@drkmm8xka";

    //  Create an instance of Cloudinary (The cloud storage we use to store images)
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);

    //  Specify parameters of how we store an image
        Map<?, ?> params = ObjectUtils.asMap(
            "use_filename",true,
            "unique_filename", true,
            "overwrite", true
        );
        String url = "URL not generated";

    //  Upload image and return the URL
        try{
            url = (String) cloudinary.uploader().upload(image.getBytes(), params).get("secure_url");
        }
        catch(IOException e){
            System.out.println("Something happened:\n" + e);
        }
       return url;
    }

    public int saveCustomerEmail(String email, Long customerId){
        Customer customer = new Customer();

        customer = repo.getReferenceById(customerId);
        customer.setEmail(email);
        repo.save(customer);
        return 200;
    }


    public ResponseEntity<?> selectCustomerDetails(Long customerId){
        Customer customer = new Customer();

        customer = repo.getReferenceById(customerId);
        CustomerDTO dto = new CustomerDTO(customer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
