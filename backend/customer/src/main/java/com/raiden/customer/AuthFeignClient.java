package com.raiden.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.raiden.customer.model.UserDto;

// Use the service name if using Eureka, or set the URL manually
@FeignClient(name = "AUTHENTICATION")
public interface AuthFeignClient {

	@GetMapping("/auth/test")
	String testCommunication(@RequestParam String message);

	@PostMapping("/auth/register")
	UserDto register(@RequestBody UserDto userdto);
}
