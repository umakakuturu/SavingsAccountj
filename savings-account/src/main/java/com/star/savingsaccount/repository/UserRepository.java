/**
 * 
 */
package com.star.savingsaccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.savingsaccount.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findById(Long id);
	
	public User findByEmailAndPassword(String email, String password);

}
