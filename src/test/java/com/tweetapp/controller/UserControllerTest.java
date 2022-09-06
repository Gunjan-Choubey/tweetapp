package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.dto.RegistrationDTO;
import com.tweetapp.model.User;
import com.tweetapp.service.UserService;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {
	
	@InjectMocks
    UserController userController;
	
	@Mock
	private UserService userService;
	
	@Test
	public void registerUserTest() {
		User user = new User();
		user.setId(null);
		user.setFirstName("Gunjan");
		user.setLastName("Choubey");
		user.setEmail("gunjan.choubey@gmail.com");
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		user.setPasswordConfirm(null);
		user.setContactNumber("9988776655");
		String result = "Successfully Registered";
		RegistrationDTO register = getRegistrationDto(user);
		when(userService.registerUser(register)).thenReturn(result);
		assertEquals(result, userController.registerUser(register));
	}
	
	private RegistrationDTO getRegistrationDto(User user) {
		RegistrationDTO regDTO = new RegistrationDTO();
		regDTO.setFirstName("Gunjan");
		regDTO.setLastName("Choubey");
		regDTO.setEmail("gunjan.choubey@gmail.com");
		regDTO.setLoginId("Gunjan@1");
		regDTO.setPassword("Ginni@1");
		regDTO.setContactNumber("9988776655");
		return regDTO;
	}
	
	@Test
	public void loginUserTest() {
		User user = new User();
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		String username = "Gunjan@1";
        String password = "Ginni@1";
		when(userService.loginUser(username, password)).thenReturn(true);
		assertEquals(true, userController.loginUser(username, password));
	}
	
	@Test
	public void forgotPasswordTest() {
		User user = new User();
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		String username = "Gunjan@1";
		String password = "Ginni@1";
		String result = "Password Reset is Successfull";
		when(userService.forgotPassword(username, password)).thenReturn(result);
		assertEquals(result, userController.forgotPassword(username, password));
	}
	
	@Test
	public void getAllUsersTest() {
		when(userService.getAllUsers()).thenReturn(Stream.of(new RegistrationDTO(),
				new RegistrationDTO()).collect(Collectors.toList()));
		assertEquals(2, userController.getAllUsers().size());
	}

	
    @Test
	public void searchByUsernameTest() {
		String username = "Gunjan@1";
		RegistrationDTO reg = new RegistrationDTO();
		//reg.setId(null);
		reg.setFirstName("Gunjan");
		reg.setLastName("Choubey");
		reg.setEmail("gunjan.choubey@gmail.com");
		reg.setLoginId("Gunjan@1");
		reg.setPassword("Ginni@1");
		//reg.setPasswordConfirm(null);
		reg.setContactNumber("9988776655");
		when(userService.searchByUsername(username)).thenReturn(reg);
		User user = new User();
		assertEquals(getRegistrationDto(user), userController.searchByUsername(username));
	}
}
