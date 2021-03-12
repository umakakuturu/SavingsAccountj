/**
 * 
 */
package com.star.savingsaccount.service;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.UserDto;

public interface UserService {
	
	public ResponseDto userLogin(UserDto userDto);

}
