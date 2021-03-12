package com.star.savingsaccount;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.star.savingsaccount.repository.UserRepository;
import com.star.savingsaccount.service.UserServiceImpl;

@SpringBootTest
class SavingsAccountApplicationTests {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

}
