package com.raiden.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raiden.customer.AuthFeignClient;
import com.raiden.customer.model.Customer;
import com.raiden.customer.model.UserDto;
import com.raiden.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private final AuthFeignClient authFeignClient;

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(AuthFeignClient authFeignClient) {
		this.authFeignClient = authFeignClient;
	}

	public UserDto registerCustomer(Customer customer) {
		String username = customer.getEmail();
		String password = customer.getCustomerName().substring(0, 4) + customer.getDateOfBirth().getYear();

		UserDto userDto = new UserDto();
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setRole("CUSTOMER");
		
		UserDto response = authFeignClient.register(userDto);
		customer.setUserId(response.getId());
		customerRepository.save(customer);

		return response;
	}

	public String callAuthService() {
		String message = "hello";
		System.out.println("calling authentication service with message: " + message);
		String response = authFeignClient.testCommunication(message);
		System.out.println("response from auth service: " + response);
		return response;
	}

}
