package org.nisum.util.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.nisum.dto.PhoneDto;
import org.nisum.entity.PhoneEntity;
import org.nisum.entity.UsersEntity;
import org.nisum.rest.response.UserResponse;
import org.nisum.util.DateUtils;
import org.springframework.stereotype.Component;

@Generated(value = "org.mapstruct.ap.MappingProcessor")
@Component
public class UsersMapperImpl implements UsersMapper {

	@Override
	public UserResponse UsersEntityToUserResponse(UsersEntity user) {
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setEmail(user.getEmail());
		response.setActive(user.getActive());
		response.setCreated(DateUtils.getDateFormatted(user.getCreated(), DateUtils.DEFAULT));
		response.setModified(DateUtils.getDateFormatted(user.getModified(), DateUtils.DEFAULT));
		response.setLastLogin(DateUtils.getDateFormatted(user.getLastLogin(), DateUtils.DEFAULT));
		response.setActive(user.getActive());
		response.setToken(user.getToken());
		response.setPhones(new ArrayList<>());
		for (PhoneEntity p : user.						getPhones()) {
			response.getPhones().add(new PhoneDto(p.getNumber(), p.getCitycode(), p.getContrycode()));
		}
		return response;
	}

	@Override
	public List<UserResponse> UsersEntityToListUserResponse(List<UsersEntity> list) {
		List<UserResponse> response = new ArrayList<>();
		for (UsersEntity user : list) {
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setName(user.getName());
			userResponse.setEmail(user.getEmail());
			userResponse.setActive(user.getActive());
			userResponse.setCreated(DateUtils.getDateFormatted(user.getCreated(), DateUtils.DEFAULT));
			userResponse.setModified(DateUtils.getDateFormatted(user.getModified(), DateUtils.DEFAULT));
			userResponse.setLastLogin(DateUtils.getDateFormatted(user.getLastLogin(), DateUtils.DEFAULT));
			userResponse.setActive(user.getActive());
			userResponse.setToken(user.getToken());
			userResponse.setPhones(new ArrayList<>());
			for (PhoneEntity p : user.getPhones()) {
				userResponse.getPhones().add(new PhoneDto(p.getNumber(), p.getCitycode(), p.getContrycode()));
			}
			response.add(userResponse);
		}
		return response;
	}


}
