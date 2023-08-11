package com.shopme.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	@Test
	public void TestEncodePassword()
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "rahulrohit";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println(encodedPassword);
		
		System.out.println(passwordEncoder.matches(rawPassword, encodedPassword));
	}

}
