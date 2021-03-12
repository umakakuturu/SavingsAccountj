/**
 * 
 */
package com.star.savingsaccount.service;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;

public interface FundtransferService {

	
	public ResponseDto fundTransfer(TransactionHistory transactionHistory) 
			throws TransactionException,RecordNotFoundException,NotInaRangeException;

}
