package com.example.kyc_backend_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KycBackendReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycBackendReportApplication.class, args);
	}

}
