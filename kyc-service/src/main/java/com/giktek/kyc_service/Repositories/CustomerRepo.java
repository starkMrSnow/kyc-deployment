package com.giktek.kyc_service.Repositories;
import com.giktek.kyc_service.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
    
}
