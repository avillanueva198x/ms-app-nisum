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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
@Api(value = "Api evaluación java", produces = "application/json", description = "Controlador para crear usuarios")
@Slf4j
public class NisumRest {

	private NisumServiceFacade nisumServiceFacade;

	public NisumRest(NisumServiceFacade leadServiceFacade) {
		this.nisumServiceFacade = leadServiceFacade;
	}

	@CrossOrigin("*")
	@ApiOperation(value = "Crear usuarios")
	@ApiResponses(value = { //
			@ApiResponse(code = 201, message = "Registro exitoso de Usuario", response = CreateResponse.class),
			@ApiResponse(code = 500, message = "Request inválido", response = GenericErrorResponse.class) })
	@PostMapping(value = "/createUser")
	public Mono<?> createUserReactive(@RequestBody UserRequest userDto) {

		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/createUser - UserRequest:{}", userDto);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {
			return nisumServiceFacade.badRequest(violations, userDto).doOnSuccess(
					r -> log.info(" [END] endpoint /api/createUser Time: " + (System.currentTimeMillis() - init)))
					.subscribeOn(Schedulers.parallel());
		}

		return nisumServiceFacade.createUserReactive(userDto)
				.doOnSuccess(r -> log.info(" [END] endpoint /api/createUser Time: " + (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}

	@CrossOrigin("*")
	@GetMapping("/user/{id}")
	public Mono<?> getUserById(@PathVariable("id") Long id) {
		long init = System.currentTimeMillis();
		log.info(" [START] endpoint /api/getUserById - id:{}", id);
		return nisumServiceFacade.getUserById(id)
				.doOnSuccess(r -> log.info(" [END] endpoint /api/getUserById Time: " + (System.currentTimeMillis() - init)))
				.subscribeOn(Schedulers.parallel());
	}

}