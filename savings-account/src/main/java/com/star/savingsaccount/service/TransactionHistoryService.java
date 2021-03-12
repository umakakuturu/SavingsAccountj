/**
 * 
 */
package com.star.savingsaccount.service;

import com.star.savingsaccount.dto.HistoryReqDto;
import com.star.savingsaccount.dto.HistoryRespDto;
import com.star.savingsaccount.exception.InvalidStatusDateException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;

public interface TransactionHistoryService {

	
	public HistoryRespDto transactionHistory(Long userId, HistoryReqDto historyReqDto) 
			throws TransactionException ,InvalidStatusDateException,RecordNotFoundException;

}
