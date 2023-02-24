package org.nisum.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.StringUtils;
import org.nisum.rest.request.UserRequest;
import org.nisum.rest.response.GenericErrorResponse;
import org.nisum.service.NisumService;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class NisumServiceFacade {

	private final NisumService nisumService;

	public NisumServiceFacade(NisumService leadService) {
		this.nisumService = leadService;
	}

	public Mono<?> createUserReactive(String authorization, UserRequest leadDto) {
		log.debug("[INI] createUserReactive facade");
		long init = System.currentTimeMillis();
		return Mono.defer(() -> Mono.just(nisumService.createUserReactive(authorization.split(" ")[1], leadDto)))
				.doOnSuccess(r -> log.debug("[END] createUserReactive facade : {}", (System.currentTimeMillis() - init)));
	}

	public Mono<?> getUserById(Long id) {
		log.debug("[INI] getUserById facade");
		long init = System.currentTimeMillis();
		return Mono.defer(() -> Mono.just(nisumService.getUserById(id)))
				.doOnSuccess(r -> log.debug("[END] getUserById facade : {}", (System.currentTimeMillis() - init)));
	}

	public Mono<?> deleteUser(Long id) {
		log.debug("[INI] deleteUser facade");
		long init = System.currentTimeMillis();
		return Mono.defer(() -> Mono.just(nisumService.deleteUser(id)))
				.doOnSuccess(r -> log.debug("[END] deleteUser facade : {}", (System.currentTimeMillis() - init)));
	}
	
	public Mono<?> getUserAll() {
		log.debug("[INI] getUserAll facade");
		long init = System.currentTimeMillis();
		return Mono.defer(() -> Mono.just(nisumService.getUserAll()))
				.doOnSuccess(r -> log.debug("[END] getUserAll facade : {}", (System.currentTimeMillis() - init)));
	}
	
	public Mono<GenericErrorResponse> badRequest(Set<ConstraintViolation<UserRequest>> violations, UserRequest dto) {
		List<String> list = new ArrayList<String>();
		for (ConstraintViolation<UserRequest> violation : violations) {
			list.add(violation.getMessage());
		}
		String msgError = (StringUtils.join(list, ", "));
		return Mono.defer(() -> Mono.just(new GenericErrorResponse(msgError)));
	}

	public String helloWord() {
		return "Hola";
	}


}
