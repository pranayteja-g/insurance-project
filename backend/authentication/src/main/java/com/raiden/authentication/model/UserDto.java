package com.raiden.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

	private String username;
	private String password;
	private String role;
}
