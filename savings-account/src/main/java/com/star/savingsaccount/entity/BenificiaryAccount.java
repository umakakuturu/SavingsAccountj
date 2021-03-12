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
@Table(name = "BENEFICIARY", uniqueConstraints = { @UniqueConstraint(columnNames = "BENEFICIARY_ID") })
public class BenificiaryAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BENEFICIARY_ID", unique = true)
	private Long beneficiaryId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "IFSC_CODE")
	private String ifscCode;

	@Column(name = "ACCOUNT_NUMBER", unique = true)
	private String accountNumber;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "BENEFICIARY_ADDED_DATE")
	private Date beneficiaryAddeddate;

	// for these all generate setter nd getter
	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getBeneficiaryAddeddate() {
		return beneficiaryAddeddate;
	}

	public void setBeneficiaryAddeddate(Date beneficiaryAddeddate) {
		this.beneficiaryAddeddate = beneficiaryAddeddate;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

}
