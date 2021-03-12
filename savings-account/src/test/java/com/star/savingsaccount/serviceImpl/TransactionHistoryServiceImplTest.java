/**
 * 
 */
package com.star.savingsaccount.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.star.savingsaccount.dto.HistoryReqDto;
import com.star.savingsaccount.dto.HistoryRespDto;
import com.star.savingsaccount.dto.TransactionHistoryDto;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.entity.User;
import com.star.savingsaccount.entity.UserAccount;
import com.star.savingsaccount.exception.InvalidStatusDateException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.repository.TransactionHistoryRepository;
import com.star.savingsaccount.repository.UserRepository;
import com.star.savingsaccount.service.TransactionHistoryServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionHistoryServiceImplTest {

	@InjectMocks
	TransactionHistoryServiceImpl transactionHistoryServiceImpl;
	@Mock
	TransactionHistoryRepository transactionHistoryRepository;
	@Mock
	UserRepository userRepository;

	@Test
	public void transactionHistoryTest()
			throws TransactionException, InvalidStatusDateException, RecordNotFoundException, ParseException {

		String fmDate = " 2020-03-10 11:42:23";
		String todate = " 2020-03-20 11:42:23";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fromDate = format.parse(fmDate);
		Date tdate = format.parse(todate);

		HistoryReqDto historyReqDto = new HistoryReqDto();
		historyReqDto.setFromDate(fmDate);
		historyReqDto.setTodate(todate);
		HistoryRespDto historyRespDto = new HistoryRespDto();
		historyRespDto.setAccountNumber("000123a");
		historyRespDto.setBalance(1000.00);

		UserAccount userAccount = new UserAccount();
		userAccount.setAccountCreatedDate(new Date());
		userAccount.setAccountNumber("123");
		userAccount.setAccountType("saving");
		userAccount.setAvailableBalance(20000.0);
		userAccount.setBranchName("ecity");
		userAccount.setIfscCode("A123");

		User user = new User();
		user.setEmail("prateek");
		user.setPassword("pal");
		user.setName("pal");
		user.setPhoneNumber("123");
		user.setUserAdress("crpf");
		user.setUserAccount(userAccount);

		TransactionHistoryDto transactionHistoryDto = new TransactionHistoryDto();
		List<TransactionHistory> list = new ArrayList<TransactionHistory>();
		transactionHistoryDto.setDate("2020-03-17");
		transactionHistoryDto.setRefNumber("123av2");
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setRefNumber("123abc");
		transactionHistory.setAmount(10000.00);
		list.add(transactionHistory);

		List<TransactionHistoryDto> trxDtos = new ArrayList<>();
		Mockito.when(userRepository.findById((long) 1)).thenReturn(Optional.of(user));

		Mockito.when(transactionHistoryRepository.findByIdAndTransactionDate(1L, fromDate, tdate)).thenReturn(list);
		HistoryRespDto result = transactionHistoryServiceImpl.transactionHistory(1L, historyReqDto);

		assertEquals("pal", user.getName());
	}

}
