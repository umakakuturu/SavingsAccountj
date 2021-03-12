/**
 * 
 */
package com.star.savingsaccount.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.savingsaccount.dto.HistoryReqDto;
import com.star.savingsaccount.dto.HistoryRespDto;
import com.star.savingsaccount.dto.TransactionHistoryDto;
import com.star.savingsaccount.entity.TransactionHistory;
import com.star.savingsaccount.entity.User;
import com.star.savingsaccount.exception.InvalidStatusDateException;
import com.star.savingsaccount.exception.RecordNotFoundException;
import com.star.savingsaccount.exception.TransactionException;
import com.star.savingsaccount.repository.TransactionHistoryRepository;
import com.star.savingsaccount.repository.UserRepository;


@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	
	@Override
	public HistoryRespDto transactionHistory(Long userId, HistoryReqDto historyReqDto)
			throws TransactionException, InvalidStatusDateException, RecordNotFoundException {

		HistoryRespDto historyRespDto = new HistoryRespDto();
		Optional<User> userOpt = userRepository.findById(userId);

		if (!userOpt.isPresent()) {
			throw new RecordNotFoundException("record not Found");
		} else {

			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date fmDate = format.parse(historyReqDto.getFromDate());
				Date toDate = format.parse(historyReqDto.getTodate());

				Date today = new Date();
				if (fmDate.after(today) || toDate.after(toDate))
					throw new InvalidStatusDateException("InvalidStatusDate GreaterThan");

				List<TransactionHistory> transactionHistoryList = transactionHistoryRepository
						.findByIdAndTransactionDate(userId, fmDate, toDate);

				User user = userOpt.get();

				historyRespDto.setAccountNumber(
						user.getUserAccount() != null ? user.getUserAccount().getAccountNumber() : "");
				historyRespDto.setUserName(user.getName());
				historyRespDto.setAddress(user.getUserAdress());
				historyRespDto.setBalance(user.getUserAccount().getAvailableBalance());
				historyRespDto.setBranch(user.getUserAccount().getBranchName());

				List<TransactionHistoryDto> trxDtos = new ArrayList<>();
				for (TransactionHistory trxHistory : transactionHistoryList) {

					TransactionHistoryDto dto = new TransactionHistoryDto();
					dto.setAmount(trxHistory.getAmount());
					dto.setNarration(trxHistory.getNarration());
					dto.setRefNumber(trxHistory.getRefNumber());
					dto.setTransactionType(trxHistory.getTransactionType());
					String strDate = format.format(trxHistory.getTransactionDate());
					dto.setDate(strDate);
					trxDtos.add(dto);
				}
				historyRespDto.setTransactionHistoryDto(trxDtos);

			} catch (ParseException e) {
				throw new RecordNotFoundException("Exception occured while processing");
			}
		}

		return historyRespDto;

	}

}
