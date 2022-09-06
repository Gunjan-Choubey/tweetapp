package com.tweetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private String loginId;
	private String password;
	private String contactNumber;
	
}