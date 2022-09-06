package com.tweetapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.RegistrationDTO;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/* 
	 * This method is used to Register as new user 
	 * @param register
	 * 
	 * @return result
	 */
	public String registerUser(RegistrationDTO register) {
		log.info("Entering Register User");
		User user = new User();
		user.setFirstName(register.getFirstName());
		user.setLastName(register.getLastName());
		user.setEmail(register.getEmail());
		user.setLoginId(register.getLoginId());
		user.setPassword(register.getPassword());
		user.setContactNumber(register.getContactNumber());
		userRepository.save(user);
		String result = "Successfully Registered";
		log.info("Exiting Register User");
		return result;
	}

	
	/* 
	 * This method is used to Login 
	 * @param username
	 * @param password
	 * 
	 * @return true/false
	 */
	public boolean loginUser(String username, String password) {
		log.info("Entering Login User - username:{} password:{}", username , password);
		User user = userRepository.findByUsername(username);
		String dbPassword = user.getPassword();
		if (dbPassword.equals(password)) { 
			log.info("Exiting Login User");
			return true;
		} else {
			log.info("Exiting Login User");
			return false;
		}
		
	}

	
	/* 
	 * This method is used to Reset Password
	 * @param username
	 * @param password
	 * 
	 * @return result
	 */
	public String forgotPassword(String username, String password) {
		log.info("Entering Forgot Password - username:{} password:{}", username , password);
		User user = userRepository.findByUsername(username);
		if ((user != null) && (user.getLoginId() != null)) {
			user.setPassword(password);
		}
		userRepository.save(user);
		String result = "Password Reset is Successfull";
		log.info("Exiting Forgot Password");
		return result;

	}

	
	/* 
	 * This method is used to get All Users
	 * 
	 * @return regDtos
	 */
	public List<RegistrationDTO> getAllUsers() {
		log.info("Entering get all Users");
		List<User> users = userRepository.findAll();
		List<RegistrationDTO> regDtos = new ArrayList<>();
		users.forEach(user -> {
			regDtos.add(getRegistrationDto(user));
		});
		log.info("Exiting get all Users");
		return regDtos;
	}

	
	/* 
	 * This method is used to search User by username 
	 * @param username
	 * 
	 * @return regDTO
	 */
	public RegistrationDTO searchByUsername(String username) {
		log.info("Entering search by Username - username:{}", username);
		User user = userRepository.findByUsername(username);
		RegistrationDTO regDTO = getRegistrationDto(user);
		log.info("Exiting search by Username");
		return regDTO;
	}

	private RegistrationDTO getRegistrationDto(User user) {
		log.info("Entering get RegistrationDTO");
		RegistrationDTO regDTO = new RegistrationDTO();
		regDTO.setFirstName(user.getFirstName());
		regDTO.setLastName(user.getLastName());
		regDTO.setEmail(user.getEmail());
		regDTO.setLoginId(user.getLoginId());
		regDTO.setPassword(user.getPassword());
		regDTO.setContactNumber(user.getContactNumber());
		log.info("Exiting get RegistrationDTO");
		return regDTO;
	}

}
