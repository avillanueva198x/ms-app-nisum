package org.nisum.util.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nisum.entity.UsersEntity;
import org.nisum.rest.response.UserResponse;

@Mapper
public interface UsersMapper {

	UsersMapper INSTANCIA = Mappers.getMapper(UsersMapper.class);

	UserResponse UsersEntityToUserResponse(UsersEntity usersEntity);

	List<UserResponse> UsersEntityToListUserResponse(List<UsersEntity> list);

}
