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

import com.star.savingsaccount.controller.TransactionController;
import com.star.savingsaccount.dto.FundTransferDto;
import com.star.savingsaccount.dto.HistoryReqDto;
import com.star.savingsaccount.dto.HistoryRespDto;
import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.TransactionType;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.exception.InvalidStatusDateException;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.service.FundtransferService;
import com.star.savingsaccount.service.TransactionHistoryService;

/**
 * @author User1
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class Transactioncontrolllertest {


	@InjectMocks
	TransactionController transactionController;
	@Mock
	TransactionHistoryService transactionHistoryService;
@Mock FundtransferService fundtransferService;
	@Test
	public void transactionHistoryTest() throws 
	TransactionException ,InvalidStatusDateException,RecordNotFoundException {
		HistoryReqDto historyReqDto = new HistoryReqDto();
		historyReqDto.setFromDate("2020-03-11");
		historyReqDto.setTodate("2020-03-16");

		HistoryRespDto historyRespDto = new HistoryRespDto();
		historyRespDto.setUserName("uma");
		historyRespDto.setAddress("banglore");
		Mockito.when(transactionHistoryService.transactionHistory
				(1L, historyReqDto)).thenReturn(historyRespDto);
		ResponseEntity<HistoryRespDto> result = transactionController.transactionHistory
				(1L, historyReqDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}
	
	@Test
    public void fundTransferTest1() throws TransactionException, 
    RecordNotFoundException, NotInaRangeException {
        FundTransferDto fundTransferDto = new FundTransferDto();
        fundTransferDto.setAccountNumber("12334");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(HttpStatus.OK.value());
        responseDto.setStatusMessage("statusMessage");
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAccountNumber("8977");
        transactionHistory.setRefNumber("7765");
        Mockito.when(fundtransferService.fundTransfer(transactionHistory)).thenReturn(responseDto);
        ResponseEntity<ResponseDto> result = transactionController.fundTransfer(fundTransferDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());

 


    }





}
