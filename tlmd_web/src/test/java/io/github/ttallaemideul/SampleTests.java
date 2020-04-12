package io.github.ttallaemideul;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleTests {

	@Test
	public void pringPwd() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pwd = bCryptPasswordEncoder.encode("1111");
		log.debug("pwd={}", pwd);
		log.debug("length={}", pwd.length());
		assertTrue(bCryptPasswordEncoder.matches("1111", pwd));
		assertTrue(bCryptPasswordEncoder.matches("1111", "$2a$10$2m18CJZqvJxFdsNiOMk/6.tMiTORoT/QFwV2NRVBwAy.mjMUwZbC6"));
	}
}
