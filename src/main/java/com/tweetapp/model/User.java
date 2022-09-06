package com.tweetapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("user")
public class User {

	@Id
	private String id;
	
	//private String userId;

	private String firstName;
	private String lastName;
	private String email;
	private String loginId;
	private String password;
	private String passwordConfirm;
	private String contactNumber;

}
