package com.example.springBootOauth2;

import com.example.springBootOauth2.entity.Account;
import com.example.springBootOauth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAuthorizationServer
@SpringBootApplication
public class SpringBootOauth2Application implements CommandLineRunner {

	@Autowired
	Account account;

	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		accountRepository.deleteAll();
		account.setUsername("raghav");
		account.setPassword("123");
		accountRepository.save(account);

		System.out.println("It works well ");
	}
}
