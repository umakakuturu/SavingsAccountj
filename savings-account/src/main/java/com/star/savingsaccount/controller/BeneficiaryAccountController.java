package com.star.savingsaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.savingsaccount.dto.BenificiaryAccountDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.service.BenificiaryAccountService;

@RestController
public class BeneficiaryAccountController {

	@Autowired
	BenificiaryAccountService benificiaryAccountService;

	@PostMapping("users/{userId}/benificiary")
	public ResponseEntity<ResponseDto> beneficiaryRegistration(@PathVariable(name = "userId") long userId,
			@RequestBody BenificiaryAccountDto benificiaryAccountDto)
			throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = benificiaryAccountService.beneficiaryRegistration
				(benificiaryAccountDto, userId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}
