/**
 * 
 */
package com.star.savingsaccount.dto;

import java.util.List;

public class HistoryRespDto {

	private String userName;
	private String accountNumber;
	private String branch;
	private String address;
	private String ifscCode;
	private Double balance;

	private List<TransactionHistoryDto> transactionHistoryDto;

	// for these all generate setter nd getter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<TransactionHistoryDto> getTransactionHistoryDto() {
		return transactionHistoryDto;
	}

	public void setTransactionHistoryDto(List<TransactionHistoryDto> transactionHistoryDto) {
		this.transactionHistoryDto = transactionHistoryDto;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
