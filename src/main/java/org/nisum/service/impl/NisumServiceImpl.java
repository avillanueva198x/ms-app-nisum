package org.nisum.service.impl;

import java.util.Date;
import java.util.List;

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
import org.nisum.util.mapper.CreateUserMapper;
import org.nisum.util.mapper.UsersMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Adolfo Villanueva
 * @version 21/05/2020
 */
@Slf4j
@Service
@AllArgsConstructor
public class NisumServiceImpl implements NisumService {

	private final UsersRepository usersRepository;
	
	private final PhoneRepository phoneRepository;  

	@Override
	public ResponseEntity<?> createUserReactive(String authorization, UserRequest userDto) {
		UsersEntity user = new UsersEntity();
		try {
			UsersEntity existe = usersRepository.findByEmail(userDto.getEmail());
			if (existe != null) {
				return new ResponseEntity<>(new GenericErrorResponse("El correo ya fue registrado"), HttpStatus.BAD_REQUEST);
			}

			BeanUtils.copyProperties(userDto, user);
			user.setPhones(null);
			user.setActive(true);
			user.setCreated(new Date());
			user.setModified(new Date());
			user.setLastLogin(new Date());
			user.setToken(authorization);
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
		CreateResponse response = CreateUserMapper.INSTANCIA.UsersEntityToCreateResponse(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserById(Long id) {
		UsersEntity user = usersRepository.findById(id).orElse(null);
		if (user != null) {			
			UserResponse response = UsersMapper.INSTANCIA.UsersEntityToUserResponse(user);
			Thread t = new Thread(new Runnable() {
				public void run() {
					user.setLastLogin(new Date());
					usersRepository.save(user);
				}
			});
			t.start();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new GenericErrorResponse("No existe usuario"), HttpStatus.NOT_FOUND);
		}
	}


	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		UsersEntity user = usersRepository.findById(id).orElse(null);
		if (user != null) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					user.setActive(false);
					user.setModified(new Date());
					user.setLastLogin(new Date());
					usersRepository.save(user);
				}
			});
			t.start();
			return new ResponseEntity<>(new GenericErrorResponse("Usuario eliminado"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new GenericErrorResponse("No existe usuario"), HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<?> getUserAll() {
		List<UserResponse> response = UsersMapper.INSTANCIA.UsersEntityToListUserResponse(usersRepository.findAll());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
