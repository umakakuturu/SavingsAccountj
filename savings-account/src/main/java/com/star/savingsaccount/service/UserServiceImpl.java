/**
 * 
 */
package com.star.savingsaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.UserDto;
import com.star.savingsaccount.entity.User;
import com.star.savingsaccount.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDto userLogin(UserDto userDto) {
		ResponseDto responseDto = new ResponseDto();

		if (userDto.getEmail().isEmpty() || userDto.getPassword().isEmpty()) {
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseDto.setStatusMessage("Please provide the required  data");
		} else {
			User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());

			if (user != null) {
				responseDto.setStatusCode(HttpStatus.OK.value());
				responseDto.setStatusMessage("Logged in successfully!!..");
			} else {
				responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseDto.setStatusMessage("Please Register");
			}
		}
		return responseDto;
	}

}
