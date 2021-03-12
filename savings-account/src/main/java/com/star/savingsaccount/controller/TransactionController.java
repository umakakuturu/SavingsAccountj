
package com.star.savingsaccount.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.savingsaccount.dto.FundTransferDto;
import com.star.savingsaccount.dto.HistoryReqDto;
import com.star.savingsaccount.dto.HistoryRespDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.exception.InvalidStatusDateException;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.service.FundtransferService;
import com.star.savingsaccount.service.TransactionHistoryService;

@RestController
public class TransactionController {

	@Autowired
	TransactionHistoryService transactionHistoryService;

	@Autowired
	private FundtransferService fundtransferService;

	@PostMapping("users/{userId}/transactions/history")
	public ResponseEntity<HistoryRespDto> transactionHistory(@PathVariable(name = "userId") long userId,
			@RequestBody HistoryReqDto historyReqDto)
			throws TransactionException, InvalidStatusDateException, RecordNotFoundException {
		HistoryRespDto responseDto = transactionHistoryService.transactionHistory(userId, historyReqDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@PostMapping("transactions/transaction")
	public ResponseEntity<ResponseDto> fundTransfer(@RequestBody FundTransferDto fundTransferDto)
			throws TransactionException, RecordNotFoundException, NotInaRangeException {
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setTransactionDate(new Date());
		BeanUtils.copyProperties(fundTransferDto, transactionHistory);
		ResponseDto responseDto = fundtransferService.fundTransfer(transactionHistory);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

}
