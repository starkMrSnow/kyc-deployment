package com.example.kyc_backend_report.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_table")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false)
    private String dateOfBirth;

    @Column(nullable=false)
    private String county;

    @Column(nullable=false)
    private String employmentStatus;

    @Column(nullable =false, unique = true)
    private String phoneNumber;

    private String selfieImageUrl = "https://res.cloudinary.com/drkmm8xka/image/upload/v1748349238/file_blsie5.jpg";

    @Column(nullable=true)
    private String frontPhotoIdUrl = null;

    @Column(nullable=true)
    private String backPhotoIdUrl = null;

    @Column(nullable=true)
    private String email = null;

    private boolean isCaptured = false;
}
