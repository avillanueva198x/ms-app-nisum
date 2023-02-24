package org.nisum.service;

import org.nisum.rest.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface NisumService {

	ResponseEntity<?> createUserReactive(String authorization, UserRequest leadDto);

	ResponseEntity<?> getUserById(Long id);

	ResponseEntity<?> deleteUser(Long id);
	
	ResponseEntity<?> getUserAll();

	
}
