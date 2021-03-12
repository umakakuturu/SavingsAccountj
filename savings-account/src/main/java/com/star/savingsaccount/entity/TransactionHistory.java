/**
 * 
 */
package com.star.savingsaccount.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="TRANSACTION", uniqueConstraints = {@UniqueConstraint(columnNames = "TRANSACTION_ID")})
public class TransactionHistory implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTION_ID" , unique = true)
	private Long transactionId;
	
	@Column(name="TRANSACTION_DATE" )
	private Date transactionDate;
	
	@Column(name="NARRATION" )
	private String narration;
	
	@Column(name="REF_NUMBER" )
	private String refNumber;
	
	@Column(name="TRANSACTION_TYPE" )
	private String transactionType;
	
	@Column(name="AMOUNT" )
	private Double amount;
	
	@Column(name="USER_ID" )
	private Long userId;
	
	private String accountNumber;
	//for these all generate setter nd getter

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	
}
