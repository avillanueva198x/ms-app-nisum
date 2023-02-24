package org.nisum.rest.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.nisum.rest.request.CredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class TokenControllerTest {

	@Autowired
	private TokenController tokenController;


	@Test
	void whenLogin() {
		CredentialsRequest token = new CredentialsRequest();
		token.setUsername("prueba");
		token.setPassword("java");

		ResponseEntity<?> result = (ResponseEntity<?>) tokenController.login(token);
		log.info("whenLogin  result  : {}", result.getStatusCode());
		assertEquals("OK", result.getStatusCode().name());
	}

}