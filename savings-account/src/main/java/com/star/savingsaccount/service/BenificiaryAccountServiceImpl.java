/**
 * 
 */
package com.star.savingsaccount.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.star.savingsaccount.dto.BenificiaryAccountDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.entity.BenificiaryAccount;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.repository.BenificiaryAccountRepository;


@Service
public class BenificiaryAccountServiceImpl implements BenificiaryAccountService {

	@Autowired
	BenificiaryAccountRepository benificiaryAccountRepository;

	@Override
	public ResponseDto beneficiaryRegistration(BenificiaryAccountDto benificiaryAccountDto, Long userId)
			throws TransactionException, NotInaRangeException {
		ResponseDto responseDto = new ResponseDto();

		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();

		if (benificiaryAccountDto.getAccountNumber().isEmpty() || benificiaryAccountDto.getIfscCode().isEmpty()) {
			responseDto.setStatusMessage("Request param is null or empty");
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		} else {
			if (benificiaryAccountDto.getAccountNumber().length() > 11
					|| benificiaryAccountDto.getAccountNumber().length() < 0)
				throw new NotInaRangeException("Account number Not in range");
			benificiaryAccount.setBeneficiaryAddeddate(new Date());
			benificiaryAccount.setUserId(userId);
			BeanUtils.copyProperties(benificiaryAccountDto, benificiaryAccount);
			benificiaryAccountRepository.save(benificiaryAccount);
			responseDto.setStatusCode(HttpStatus.OK.value());
			responseDto.setStatusMessage("Beneficiary added successfully");

		}

		return responseDto;

	}

}
