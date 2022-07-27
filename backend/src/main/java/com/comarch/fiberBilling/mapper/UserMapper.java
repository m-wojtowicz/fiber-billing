package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.UserDTO;
import com.comarch.fiberBilling.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);

    List<UserDTO> userListToUserDtosList(List<User> users);

    List<User> userDtosListToUserList(List<UserDTO> usersDTOs);
}
