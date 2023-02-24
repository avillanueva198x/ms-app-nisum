package org.nisum.rest.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.nisum.facade.NisumServiceFacade;
import org.nisum.rest.request.UserRequest;
import org.nisum.rest.response.CreateResponse;
import org.nisum.rest.response.GenericErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Controlador para creación de usuarios
 * 
 * @author : Adolfo Villanueva
 * @version: 21/02/2023
 */
@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Api evaluación java", produces = "application/json", description = "Controlador para crear usuarios")
@Slf4j
public class UsersController {
	
	@Autowired
	private NisumServiceFacade nisumServiceFacade;

	@CrossOrigin("*")
	@GetMapping("/helloWord")
	public String helloWord() {
		return nisumServiceFacade.helloWord();
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Crear usuarios")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Registro exitoso de Usuario", response = CreateResponse.class),
			@ApiResponse(code = 500, message = "Request inválido", response = GenericErrorResponse.class) })
	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<?> createUserReactive(@RequestHeader(value = "Authorization", required = true) String authorization, @RequestBody UserRequest userDto) {

		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/createUser - UserRequest:{} , authorization : {}", userDto, authorization);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {
			return nisumServiceFacade.badRequest(violations, userDto).doOnSuccess(
					r -> log.info(" [END] endpoint /api/createUser Time: " + (System.currentTimeMillis() - init)))
					.subscribeOn(Schedulers.parallel());
		}

		return nisumServiceFacade.createUserReactive(authorization, userDto)
				.doOnSuccess(r -> log.info(" [END] endpoint /api/createUser Time: {}", (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Consultar usuario por Id")
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<?> getUserById(@PathVariable("id") Long id) {
		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/getUserById - id:{}", id);
		return nisumServiceFacade.getUserById(id)
				.doOnSuccess(r -> log.info(" [END] endpoint /api/getUserById Time: {}", (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Listar todos los usuarios registrados")
	@GetMapping(value = "/user/all", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<?> getUserAll() {
		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/getUserAll ");
		return nisumServiceFacade.getUserAll()
				.doOnSuccess(r -> log.info(" [END] endpoint /api/getUserAll Time: {}" , (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}


	@CrossOrigin("*")
	@ApiOperation(value = "Eliminar usuario")
	@DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<?> deleteUser(@PathVariable("id") Long id) {
		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/deleteUser ");
		return nisumServiceFacade.deleteUser(id)
				.doOnSuccess(r -> log.info(" [END] endpoint /api/deleteUser Time: {}" , (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}
}