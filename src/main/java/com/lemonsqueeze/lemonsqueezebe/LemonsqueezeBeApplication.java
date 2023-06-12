package com.lemonsqueeze.lemonsqueezebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lemonsqueeze.lemonsqueezebe.model.repository.UserRepository;

@SpringBootApplication
public class LemonsqueezeBeApplication{

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LemonsqueezeBeApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
