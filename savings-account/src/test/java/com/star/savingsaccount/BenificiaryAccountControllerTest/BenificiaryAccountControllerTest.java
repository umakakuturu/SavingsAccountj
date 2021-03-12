/**
 * 
 */
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

import com.star.savingsaccount.controller.BeneficiaryAccountController;
import com.star.savingsaccount.dto.BenificiaryAccountDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.service.BenificiaryAccountService;

/**
 * @author User1
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BenificiaryAccountControllerTest {

	@InjectMocks
	BeneficiaryAccountController benificiaryAccountController;
	
	@Mock
	BenificiaryAccountService beneficiaryAccountService;

	@Test
	public void benificiaryRegistrationTest() throws TransactionException,NotInaRangeException{
		BenificiaryAccountDto benificiaryAccountDto = new BenificiaryAccountDto();
		Long userId=1L;
		benificiaryAccountDto.setIfscCode("dhfj88");
		benificiaryAccountDto.setName("uma");
		ResponseDto responseDto = new ResponseDto();
		Mockito.when(beneficiaryAccountService.beneficiaryRegistration
				(benificiaryAccountDto,userId)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = benificiaryAccountController
				.beneficiaryRegistration(userId,benificiaryAccountDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

}
