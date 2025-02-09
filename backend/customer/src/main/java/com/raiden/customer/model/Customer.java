package com.raiden.customer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String customerName;
	private LocalDate dateOfBirth;
	private String gender;
	private String contactNumber;
	@Column(unique = true)
	private String email;
	private String address;
	private String occupation;
	private Double annualIncome;
	private String bankDetails;
	
	@ElementCollection
	private List<String> kycDocuments;
	
	@Enumerated(EnumType.STRING)
	private KycStatus kycStatus;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Long userId;
	
}
