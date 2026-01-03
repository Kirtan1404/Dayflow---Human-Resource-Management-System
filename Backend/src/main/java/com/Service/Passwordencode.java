package com.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Passwordencode {

	@Bean
	public PasswordEncoder getpasswordEncode()
	{
		return new BCryptPasswordEncoder() ;
	}

}
