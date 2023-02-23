package org.nisum.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.nisum.dto.PhoneDto;
import org.nisum.entity.PhoneEntity;
import org.nisum.entity.UsersEntity;
import org.nisum.repository.PhoneRepository;
import org.nisum.repository.UsersRepository;
import org.nisum.rest.request.PhoneRequest;
import org.nisum.rest.request.UserRequest;
import org.nisum.rest.response.CreateResponse;
import org.nisum.rest.response.GenericErrorResponse;
import org.nisum.rest.response.UserResponse;
import org.nisum.service.NisumService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Adolfo Villanueva
 * @version 21/05/2020
 */
@Slf4j
@Service
public class NisumServiceImpl implements NisumService {

	private final UsersRepository usersRepository;
	private final PhoneRepository phoneRepository;

	public NisumServiceImpl(@SuppressWarnings("all") final UsersRepository usersRepository,
			@SuppressWarnings("all") final PhoneRepository phoneRepository) {
		this.usersRepository = usersRepository;
		this.phoneRepository = phoneRepository;
	}

	@Override
	public ResponseEntity<?> createUserReactive(UserRequest userDto) {
		UsersEntity user = new UsersEntity();
		try {
			UsersEntity existe = usersRepository.findByEmail(userDto.getEmail());
			if (existe != null) {
				return new ResponseEntity<>(new GenericErrorResponse("Usuario ya registrado"), HttpStatus.BAD_REQUEST);
			}

			BeanUtils.copyProperties(userDto, user);
			user.setPhones(null);
			user.setActive(true);
			user.setCreated(new Date());
			user.setModified(new Date());
			user.setLastLogin(new Date());
			user.setToken("uuid");
			usersRepository.save(user);
			for (PhoneRequest p : userDto.getPhones()) {
				PhoneEntity phone = new PhoneEntity();
				phone.setNumber(p.getNumber());
				phone.setCitycode(p.getCitycode());
				phone.setContrycode(p.getContrycode());
				phone.setIdUser(user.getId());
				phoneRepository.save(phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(" Error createUserReactive ", e.getMessage());
			return new ResponseEntity<>(new GenericErrorResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		CreateResponse response = new CreateResponse();
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setActive(user.getActive());
		response.setToken(user.getToken());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserById(Long id) {
		UsersEntity user = usersRepository.findById(id).orElse(null);
		if (user != null) {
			UserResponse response = new UserResponse();
			BeanUtils.copyProperties(user, response);
			response.setPhones(new ArrayList<>());
			for (PhoneEntity p : user.getPhones()) {
				response.getPhones().add(new PhoneDto(p.getNumber(), p.getCitycode(), p.getContrycode()));
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new GenericErrorResponse("No existe usuario"), HttpStatus.NOT_FOUND);
		}
	}

}
