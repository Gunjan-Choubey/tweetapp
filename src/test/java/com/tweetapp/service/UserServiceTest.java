package com.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
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
import com.tweetapp.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	
	@InjectMocks
    UserService userService;
	
	@Mock
	private UserRepository userRepository;
	

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
		when(userRepository.save(user)).thenReturn(user);
		RegistrationDTO regDTO = getRegistrationDto(user);
		assertEquals(result, userService.registerUser(regDTO));
	}
	
	private RegistrationDTO getRegistrationDto(User user) {
		RegistrationDTO regDTO = new RegistrationDTO();
		regDTO.setFirstName(user.getFirstName());
		regDTO.setLastName(user.getLastName());
		regDTO.setEmail(user.getEmail());
		regDTO.setLoginId(user.getLoginId());
		regDTO.setPassword(user.getPassword());
		regDTO.setContactNumber(user.getContactNumber());
		return regDTO;
	}
	
	@Test
	public void loginUserTest() {
		User user = new User();
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		String username = "Gunjan@1";
        String password = "Ginni@1";
		lenient().when(userRepository.findByUsername(username)).thenReturn(user);
		String dbPassword = user.getPassword();
		
		assertEquals(dbPassword.equals(password), userService.loginUser(username, password));
	}
	
	@Test
	public void loginUserTestcase() {
		User user = new User();
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		String username = "Gunjan@1";
        String password = "G@1";
		lenient().when(userRepository.findByUsername(username)).thenReturn(user);
		String dbPassword = user.getPassword();
		
		assertEquals(dbPassword.equals(password), userService.loginUser(username, password));
	}
	
	@Test
	public void forgotPasswordTest() {
		User user = new User();
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		String username = "Gunjan@1";
		String password = "Ginni@1";
		when(userRepository.findByUsername(username)).thenReturn(user);
		String result = "Password Reset is Successfull";
		when(userRepository.save(user)).thenReturn(user);
	
		assertEquals(result, userService.forgotPassword(username, password));

	}
	
	@Test
	public void getAllUsersTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(new User(),
				new User()).collect(Collectors.toList()));
		assertEquals(2, userService.getAllUsers().size());
	}
	//"Gunjan","Choubey","gunjan.choubey@gmail.com","Gunjan@1","Ginni@1","9988776655"
	
	
	@Test
	public void searchByUsernameTest() {
		String username = "Gunjan@1";
		User user = new User();
		user.setId(null);
		user.setFirstName("Gunjan");
		user.setLastName("Choubey");
		user.setEmail("gunjan.choubey@gmail.com");
		user.setLoginId("Gunjan@1");
		user.setPassword("Ginni@1");
		user.setPasswordConfirm(null);
		user.setContactNumber("9988776655");
		when(userRepository.findByUsername(username)).thenReturn(user);
		assertEquals(getRegistrationDto(user), userService.searchByUsername(username));
	}
	
	

}
