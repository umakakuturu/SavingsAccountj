/**
 * 
 */
package com.star.savingsaccount.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.entity.BenificiaryAccount;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.entity.User;
import com.star.savingsaccount.entity.UserAccount;
import com.star.savingsaccount.exception.NotInaRangeException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.repository.BenificiaryAccountRepository;
import com.star.savingsaccount.repository.TransactionHistoryRepository;
import com.star.savingsaccount.repository.UserAccountRepository;
import com.star.savingsaccount.repository.UserRepository;
import com.star.savingsaccount.service.FundtransferServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FundtransferServiceImplTest {

	@InjectMocks
	FundtransferServiceImpl fundtransferServiceImpl;
	@Mock
	BenificiaryAccountRepository benificiaryAccountRepository;

	@Mock
	UserAccountRepository accountRepository;

	@Mock
	UserRepository userRepository;
	@Mock
	TransactionHistoryRepository transactionHistoryRepository;

	@Test(expected = RecordNotFoundException.class)
	public void fundTransferRecordNotFoundTest()
			throws TransactionException, RecordNotFoundException, NotInaRangeException {

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.OK.value());
		responseDto.setStatusMessage("Logged in successfully!!..");

		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccountNumber("123");
		transactionHistory.setAmount(100.00);
		transactionHistory.setTransactionId((long) 1);
		transactionHistory.setUserId(1L);
		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		Mockito.when(benificiaryAccountRepository.findByAccountNumber("1234"))
				.thenReturn(Optional.of(benificiaryAccount));
		ResponseDto res = fundtransferServiceImpl.fundTransfer(transactionHistory);
		assertNotEquals(res.getStatusCode(), responseDto.getStatusCode());

	}

	@Test
	public void fundTransferTest() throws TransactionException, RecordNotFoundException, NotInaRangeException {

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.OK.value());
		responseDto.setStatusMessage("Transaction Successfull");
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccountNumber("123");
		transactionHistory.setAmount(100.00);
		transactionHistory.setTransactionId(1L);
		transactionHistory.setUserId(1L);
		transactionHistory.setNarration("gift");
		transactionHistory.setRefNumber("123");

		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setName("prateek");
		benificiaryAccount.setIfscCode("A123");
		benificiaryAccount.setAccountNumber("456");
		benificiaryAccount.setUserId(1L);
		benificiaryAccount.setBeneficiaryId(1L);
		benificiaryAccount.setBeneficiaryAddeddate(new Date());

		Mockito.when(benificiaryAccountRepository.findByAccountNumber(Mockito.anyString()))
				.thenReturn(Optional.of(benificiaryAccount));

		UserAccount userAccount = new UserAccount();
		userAccount.setAccountCreatedDate(new Date());
		userAccount.setAccountNumber("09876543212");
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
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		ResponseDto res = fundtransferServiceImpl.fundTransfer(transactionHistory);
		assertEquals(res.getStatusMessage(), responseDto.getStatusMessage());
	}

	@Test
	public void noUserFoundTest() throws TransactionException, RecordNotFoundException, NotInaRangeException {

		ResponseDto responseDto = new ResponseDto();

		responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseDto.setStatusMessage("Record not found");

		BenificiaryAccount benificiaryAccount = new BenificiaryAccount();
		benificiaryAccount.setName("prateek");
		benificiaryAccount.setIfscCode("A123");
		benificiaryAccount.setAccountNumber("456");
		benificiaryAccount.setUserId(1L);
		benificiaryAccount.setBeneficiaryId(1L);
		benificiaryAccount.setBeneficiaryAddeddate(new Date());

		Mockito.when(benificiaryAccountRepository.findByAccountNumber(Mockito.anyString()))
				.thenReturn(Optional.of(benificiaryAccount));

		User user = new User();
		user.setEmail("prateek");

		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setAccountNumber("123");
		transactionHistory.setAmount(100.00);
		transactionHistory.setTransactionId(1L);
		transactionHistory.setUserId(1L);
		transactionHistory.setNarration("gift");
		transactionHistory.setRefNumber("123");
		// user.setUserAccount(userAccount);

		Mockito.when(userRepository.findById(100L)).thenReturn(Optional.of(user));

		ResponseDto res = fundtransferServiceImpl.fundTransfer(transactionHistory);
		assertEquals(res.getStatusMessage(), responseDto.getStatusMessage());

	}

}
