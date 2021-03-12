
package com.star.savingsaccount.BenificiaryAccountControllerTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.star.savingsaccount.controller.UserController;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.UserDto;
import com.star.savingsaccount.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;
	@Mock
	UserService userService;

	@Test
	public void userLoginTest() {
		UserDto userDto = new UserDto();
		userDto.setPassword("baiu");
		userDto.setEmail("baiu@gmail.com");

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Please provide the required  data");

		Mockito.when(userService.userLogin(userDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = userController.userLogin(userDto);

		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

}
