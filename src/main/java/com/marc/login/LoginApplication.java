package com.marc.login;

import com.marc.login.Service.LoginService;
import com.marc.login.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Autowired
	private LoginService loginService;

	@Override
	public void run(String... args) throws Exception {
		loginService.save(new Login(
				null,
				"user1",
				"pass1",
				"Marc"
		));
		loginService.save(new Login(
				null,
				"user2",
				"pass2",
				"Kristoffer"
		));
	}
}
