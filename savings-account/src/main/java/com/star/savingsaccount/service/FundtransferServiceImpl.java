
package com.star.savingsaccount.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.star.savingsaccount.dto.ResponseDto;
import com.star.savingsaccount.dto.TransactionType;
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

@Service
public class FundtransferServiceImpl implements FundtransferService {

	private static Logger log = LoggerFactory.getLogger(FundtransferServiceImpl.class);

	@Autowired
	private BenificiaryAccountRepository benificiaryAccountRepository;
	@Autowired
	private UserAccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	@Transactional
	public ResponseDto fundTransfer(TransactionHistory transactionHistory)
			throws TransactionException, RecordNotFoundException, NotInaRangeException {
		String accountNumber = transactionHistory.getAccountNumber();

		ResponseDto responseDto = new ResponseDto();
		Optional<BenificiaryAccount> benificiaryAccount = benificiaryAccountRepository
				.findByAccountNumber(accountNumber);

		if (!benificiaryAccount.isPresent())
			throw new RecordNotFoundException("Record not found");
		else {
			Optional<User> user = userRepository.findById(transactionHistory.getUserId());

			if (user.isPresent()) {

				Optional<UserAccount> recieverAccount = accountRepository.findByAccountNumber(accountNumber);
				if (!recieverAccount.isPresent())
					throw new TransactionException("No acocunt record found for the id: " + accountNumber);

				UserAccount senderAccount = user.get().getUserAccount();

				if (senderAccount.getAvailableBalance() < transactionHistory.getAmount())
					throw new NotInaRangeException("Insufficient balance");
				else {

					try {

						// Set the Debit Record Histroy
						transactionHistory.setTransactionDate(new Date());
						transactionHistory.setAccountNumber(senderAccount.getAccountNumber());
						transactionHistory.setRefNumber(generateRefrence(10));
						transactionHistory.setTransactionType(TransactionType.DEBIT.name());
						transactionHistoryRepository.save(transactionHistory);

						// Set the Credit Record Histroy
						TransactionHistory recieverTransactionHistory = new TransactionHistory();
						recieverTransactionHistory.setAmount(transactionHistory.getAmount());
						recieverTransactionHistory.setNarration("credited");
						recieverTransactionHistory.setRefNumber(generateRefrence(10));
						recieverTransactionHistory.setTransactionDate(new Date());
						recieverTransactionHistory.setTransactionType(TransactionType.CREDIT.name());
						recieverTransactionHistory.setAccountNumber(accountNumber);
						recieverTransactionHistory.setUserId(transactionHistory.getUserId());
						transactionHistoryRepository.save(recieverTransactionHistory);

						senderAccount.setAvailableBalance(
								senderAccount.getAvailableBalance() - transactionHistory.getAmount());
						recieverAccount.get().setAvailableBalance(
								recieverAccount.get().getAvailableBalance() + transactionHistory.getAmount());

						accountRepository.save(senderAccount);
						responseDto.setStatusCode(HttpStatus.OK.value());
						responseDto.setStatusMessage("Transaction Successfull");

					} catch (Exception e) {
						responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
						responseDto.setStatusMessage("Internal server error");
						throw e; // logic to this exception
					}

				}

			} else {
				responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseDto.setStatusMessage("Record not found");
			}

		}
		return responseDto;
	}

	static String generateRefrence(int n) {

		// chose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString`
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (alphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}
