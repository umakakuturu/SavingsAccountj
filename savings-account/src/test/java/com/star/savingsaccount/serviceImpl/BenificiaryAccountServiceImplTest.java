/**
 * 
 */
package com.star.savingsaccount.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

import com.star.savingsaccount.dto.BenificiaryAccountDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.entity.BenificiaryAccount;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.repository.BenificiaryAccountRepository;
import com.star.savingsaccount.service.BenificiaryAccountServiceImpl;
import com.star.savingsaccount.service.TransactionHistoryService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BenificiaryAccountServiceImplTest {
	@InjectMocks
	BenificiaryAccountServiceImpl beneficiaryAccountServiceImpl;

	@Mock
	TransactionHistoryService transactionHistoryService;

	@Mock
	BenificiaryAccountRepository benificiaryAccountRepository;

	@Test
	public void beneficiaryRegistrationTest() throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Beneficiary added successfully");
		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setAccountNumber("123");
		benificiaryAccount.setName("uma");
		BenificiaryAccountDto benificiaryAccountDto = new BenificiaryAccountDto();
		benificiaryAccountDto.setIfscCode("A123");
		benificiaryAccountDto.setAccountNumber("456");
		Mockito.when(benificiaryAccountRepository.save(benificiaryAccount)).thenReturn(benificiaryAccount);
		ResponseDto result = beneficiaryAccountServiceImpl.beneficiaryRegistration(benificiaryAccountDto, 1L);
		assertEquals(responseDto.getStatusMessage(), result.getStatusMessage());

	}

	@Test
	public void invalidDataBeneficiaryRegistrationTest() throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("Request param is null or empty");
		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setAccountNumber("123");
		benificiaryAccount.setName("uma");
		BenificiaryAccountDto benificiaryAccountDto = new BenificiaryAccountDto();
		benificiaryAccountDto.setIfscCode("");
		benificiaryAccountDto.setAccountNumber("");
		Mockito.when(benificiaryAccountRepository.save(benificiaryAccount)).thenReturn(benificiaryAccount);
		ResponseDto result = beneficiaryAccountServiceImpl.beneficiaryRegistration(benificiaryAccountDto, 1L);
		assertEquals(responseDto.getStatusMessage(), result.getStatusMessage());

	}

	@Test
	public void invalidBeneficiaryRegistrationTest() throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("some thing went wrong plz try again");
		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setAccountNumber("123");
		benificiaryAccount.setName("uma");
		BenificiaryAccountDto benificiaryAccountDto = new BenificiaryAccountDto();
		benificiaryAccountDto.setIfscCode("");

		Mockito.when(benificiaryAccountRepository.save(benificiaryAccount)).thenReturn(benificiaryAccount);
		ResponseDto result = beneficiaryAccountServiceImpl.beneficiaryRegistration(benificiaryAccountDto, 1L);
		assertEquals(responseDto.getStatusMessage(), result.getStatusMessage());

	}

	@Test
	public void invalidRangeBeneficiaryRegistrationTest() throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseDto.setStatusMessage("some thing went wrong plz try again");
		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setAccountNumber("1234567");
		benificiaryAccount.setName("uma");
		BenificiaryAccountDto benificiaryAccountDto = new BenificiaryAccountDto();
		benificiaryAccountDto.setIfscCode("A123");
		// benificiaryAccountDto.setAccountNumber("45612378910341");

		Mockito.when(benificiaryAccountRepository.save(benificiaryAccount)).thenReturn(benificiaryAccount);
		ResponseDto result = beneficiaryAccountServiceImpl.beneficiaryRegistration(benificiaryAccountDto, 1L);
		assertEquals(responseDto.getStatusMessage(), result.getStatusMessage());

	}

}
