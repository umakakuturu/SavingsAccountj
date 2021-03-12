/**
 * 
 */
package com.star.savingsaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.savingsaccount.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	
	Optional<UserAccount> findByAccountNumber(String account);


}
