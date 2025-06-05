package com.giktek.email_service.Services;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Properties;
// import java.util.Random;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;;


@Service
public class EmailService {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void sendMail(String email) throws AddressException, MessagingException{

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lwalacecil13@gmail.com", "aeot qvfo jnoa odun");
            }
        });

        MimeMessage message = new MimeMessage(session); 
        
        message.setFrom(new InternetAddress("lwalacecil13@gmail.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
        message.setSubject("GikTek Interns KYC Customer Onboarding Verification");

        // Random random = new Random();
        // int code  = random.nextInt(1000, 9000);

        String msg = "Thank you for submitting your details! Welcome to GikTek!";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
    
}
