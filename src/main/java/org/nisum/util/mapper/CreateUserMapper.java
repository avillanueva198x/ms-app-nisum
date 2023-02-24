package org.nisum.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nisum.entity.UsersEntity;
import org.nisum.rest.response.CreateResponse;

@Mapper
public interface CreateUserMapper{

	CreateUserMapper INSTANCIA = Mappers.getMapper(CreateUserMapper.class);

	CreateResponse UsersEntityToCreateResponse(UsersEntity usersEntity);

}
