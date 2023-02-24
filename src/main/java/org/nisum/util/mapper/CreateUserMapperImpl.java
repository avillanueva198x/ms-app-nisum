package org.nisum.util.mapper;

import javax.annotation.Generated;

import org.nisum.entity.UsersEntity;
import org.nisum.rest.response.CreateResponse;
import org.nisum.util.DateUtils;
import org.springframework.stereotype.Component;

@Generated(value = "org.mapstruct.ap.MappingProcessor")
@Component
public class CreateUserMapperImpl implements CreateUserMapper {

	@Override
	public CreateResponse UsersEntityToCreateResponse(UsersEntity user) {
		CreateResponse response = new CreateResponse();
		response.setId(user.getId());
		response.setCreated(DateUtils.getDateFormatted(user.getCreated(), DateUtils.DEFAULT));
		response.setModified(DateUtils.getDateFormatted(user.getModified(), DateUtils.DEFAULT));
		response.setLastLogin(DateUtils.getDateFormatted(user.getLastLogin(), DateUtils.DEFAULT));
		response.setActive(user.getActive());
		response.setToken(user.getToken());
		return response;
	}

}
