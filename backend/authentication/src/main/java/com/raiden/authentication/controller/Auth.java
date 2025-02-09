package com.raiden.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raiden.authentication.model.User;
import com.raiden.authentication.model.UserDto;
import com.raiden.authentication.service.AuthenticationService;
import com.raiden.authentication.service.CustomUserDetailsService;
import com.raiden.authentication.service.JwtService;

@RestController
@RequestMapping("/auth")
public class Auth {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
		User createdUser = authService.registerUser(userDto.getUsername(), userDto.getPassword(), userDto.getRole());
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
		
		String token = authService.login(loginRequest);

		// Return token in the response
		return ResponseEntity.ok(token);
	}

	@GetMapping("/test")
	public String testCommunication(@RequestParam String message) {
		System.out.println("recieved message: " + message);
		String response = message + " world";
		System.out.println("sending response: " + response);
		return response;
	}
}
