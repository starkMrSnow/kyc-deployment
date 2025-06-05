package com.example.kyc_backend_report.repository;

import java.util.List;
import com.example.kyc_backend_report.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByOrderByCustomerIdAsc(); 
    List<Customer> findAllByOrderByFirstNameAsc();
    Customer findByEmail(String email);

}
