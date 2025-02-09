package com.raiden.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raiden.authentication.model.Role;
import com.raiden.authentication.model.User;
import com.raiden.authentication.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(String username, String password, String roleString) {

		Role role = Role.valueOf(roleString.toUpperCase());

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(role);
		userRepository.save(user);
		return user;
	}

	public String login(User loginRequest) {
		// Fetch user details
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

		// Validate password
		if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
			return "Invalid username or password";
		}

		// Generate JWT token
		String token = jwtService.generateToken(userDetails.getUsername());
		
		return token;

	}
}
