package com.star.savingsaccount.dto;

public class BenificiaryAccountDto {
	
	
	private String name;
	private String ifscCode;
	private String accountNumber;
	//for these all generate setter nd getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

}
