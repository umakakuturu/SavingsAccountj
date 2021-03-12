/**
 * 
 */
package com.star.savingsaccount.serviceImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.UserDto;
import com.star.savingsaccount.entity.User;
import com.star.savingsaccount.repository.UserRepository;
import com.star.savingsaccount.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class userserviceimpltest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	public void userLoginTest() {

		UserDto userDto = new UserDto();
		userDto.setEmail("uma");
		userDto.setPassword("mahi");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.OK.value());
		responseDto.setStatusMessage("Logged in successfully!!..");

		User user = new User();
		user.setEmail("uma");
		user.setPassword("mahi");
		user.setPhoneNumber("1234");
		Mockito.when(userRepository.findByEmailAndPassword(Mockito.anyString(), 
				Mockito.anyString())).thenReturn(user);
		ResponseDto res = userServiceImpl.userLogin(userDto);
		assertEquals(responseDto.getStatusCode(), res.getStatusCode());

	}

	@Test
	public void negativeUserLoginTest() {

		UserDto userDto = new UserDto();
		userDto.setEmail("");
		userDto.setPassword("");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Please provide the required  data");

		User user = new User();
		user.setEmail("uma");
		user.setPassword("mahi");
		user.setPhoneNumber("1234");
		Mockito.when(userRepository.findByEmailAndPassword(Mockito.anyString(), 
				Mockito.anyString())).thenReturn(user);
		ResponseDto res = userServiceImpl.userLogin(userDto);
		assertEquals(responseDto.getStatusCode(), res.getStatusCode());

	}

	@Test
	public void NoUserLoginTest() {

		UserDto userDto = new UserDto();
		userDto.setEmail("uma");
		userDto.setPassword("mahi");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseDto.setStatusMessage("Please Register");

		User user = new User();
		user.setEmail("abc");
		user.setPassword("def");
		user.setPhoneNumber("1234");
		Mockito.when(userRepository.findByEmailAndPassword("jjal", "hjhal")).thenReturn(user);
		ResponseDto res = userServiceImpl.userLogin(userDto);
		assertEquals(responseDto.getStatusCode(), res.getStatusCode());

	}

}
