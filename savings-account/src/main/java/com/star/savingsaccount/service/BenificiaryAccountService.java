/**
 * 
 */
package com.star.savingsaccount.service;

import com.star.savingsaccount.dto.BenificiaryAccountDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.TransactionException;

public interface BenificiaryAccountService {
	
	public ResponseDto beneficiaryRegistration(BenificiaryAccountDto benificiaryAccountDto,Long userId)
			throws TransactionException, NotInaRangeException;
	

}
