package com.giktek.email_service.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.giktek.email_service.Services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {

     @Autowired
    EmailService emailService;
    
    @GetMapping("send-email")
    public ResponseEntity<?> sendEmail(){
         try {
            emailService.sendMail("lwalacecil13@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Email not sent");
        }
        return ResponseEntity.ok("Email sent");
    }
}
