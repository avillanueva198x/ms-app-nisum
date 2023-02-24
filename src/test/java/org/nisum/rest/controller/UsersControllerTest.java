package org.nisum.rest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.nisum.rest.request.PhoneRequest;
import org.nisum.rest.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class UsersControllerTest {

	@Autowired
	private UsersController usersController;

	@Test
	void whenSayHello_shouldReturnStringHola() {
		String result = usersController.helloWord();
		assertEquals("Hola", result);
	}

	@Test
	void whenCreateUser() {
		String authorization = "Bear token_test";
		UserRequest userDto = new UserRequest();
		userDto.setEmail("avillanueva198x@gmail.com");
		userDto.setName("Adolfo Villanueva");
		userDto.setPassword("a1bans2alabinbomban&Y");
		List<PhoneRequest> list = new ArrayList<PhoneRequest>();
		list.add(new PhoneRequest("931871964", "1", "51"));
		userDto.setPhones(list);

		ResponseEntity<?> result = (ResponseEntity<?>) usersController.createUserReactive(authorization, userDto)
				.block();
		log.info("whenCreateUser  result  : {}", result.getStatusCode());
		assertEquals("OK", result.getStatusCode().name());
	}

    @Test
    void whengetUserById() {
    	ResponseEntity<?> result = (ResponseEntity<?>) usersController.getUserById(new Long(1)).block();
		assertEquals("OK", result.getStatusCode().name());
    }

    @Test
    void whenDeleteUser() {
    	ResponseEntity<?> result = (ResponseEntity<?>) usersController.deleteUser(new Long(1)).block();
		assertEquals("OK", result.getStatusCode().name());
    }
    
    @Test
    void whenGetUserAll() {
    	ResponseEntity<?> result = (ResponseEntity<?>) usersController.getUserAll().block();
		assertEquals("OK", result.getStatusCode().name());
    }
}