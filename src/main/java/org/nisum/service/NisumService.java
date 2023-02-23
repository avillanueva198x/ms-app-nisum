package org.nisum.service;

import org.nisum.rest.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface NisumService {

	ResponseEntity<?> createUserReactive(UserRequest leadDto);

	ResponseEntity<?> getUserById(Long id);

}
