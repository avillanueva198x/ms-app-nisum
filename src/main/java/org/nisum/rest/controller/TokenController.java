package org.nisum.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.nisum.dto.UserDto;
import org.nisum.repository.SecurityRepository;
import org.nisum.rest.request.CredentialsRequest;
import org.nisum.rest.response.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@Api(value = "Api evaluación java", produces = "application/json", description = "Controlador para generación de tokens")
@Slf4j
@AllArgsConstructor
public class TokenController {

	private SecurityRepository SecurityRepository;

	@CrossOrigin("*")
	@ApiOperation(value = "Genera Token de acceso")
	@PostMapping(value = "/token")
	public ResponseEntity<?> login(@RequestBody CredentialsRequest credentials) {
		int existeUser = SecurityRepository.existUserPassword(credentials.getUsername(), credentials.getPassword());
		if (existeUser == 0) {
			return new ResponseEntity<>(new GenericErrorResponse("Credenciales Inválidas"), HttpStatus.NOT_FOUND);
		}
		String token = getJWTToken(credentials.getUsername());
		UserDto user = new UserDto();
		user.setUser(credentials.getUsername());
		user.setToken(token);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		log.info("Create token successful");
		return "Bearer " + token;
	}
}
