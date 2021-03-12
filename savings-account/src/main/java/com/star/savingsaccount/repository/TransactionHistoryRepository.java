/**
 * 
 */
package com.star.savingsaccount.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.star.savingsaccount.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
	
	
	@Query("from TransactionHistory o where o.userId= :id and o.transactionDate between :fmDate and :toDate")
	public List<TransactionHistory> findByIdAndTransactionDate(@Param("id") Long id,
			@Param("fmDate") Date fmDate, @Param("toDate") Date toDate);

}
