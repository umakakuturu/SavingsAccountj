
package com.star.savingsaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.UserDto;
import com.star.savingsaccount.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;


	@PostMapping("users/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody UserDto userDto) {
		ResponseDto responseDto = userService.userLogin(userDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
