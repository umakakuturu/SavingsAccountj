/**
 * 
 */
package com.star.savingsaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.savingsaccount.entity.BenificiaryAccount;

public interface BenificiaryAccountRepository extends JpaRepository<BenificiaryAccount, Long> {

//	Optional<BenificiaryAccount> findByAccountNumber(String accountNumber);
	
	Optional<BenificiaryAccount> findByAccountNumber(String account);

}
