package com.raiden.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raiden.customer.model.Customer;
import com.raiden.customer.model.UserDto;
import com.raiden.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/register")
	public UserDto register(@RequestBody Customer customer) {
		return customerService.registerCustomer(customer);
	}

	@GetMapping("/test-communication")
	public String testCommunication() {
		return customerService.callAuthService();
	}
}
