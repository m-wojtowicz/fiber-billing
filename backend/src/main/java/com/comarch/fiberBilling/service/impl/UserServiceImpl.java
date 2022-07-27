package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.UserMapper;
import com.comarch.fiberBilling.model.dto.UserDTO;
import com.comarch.fiberBilling.model.entity.User;
import com.comarch.fiberBilling.repository.UserRepository;
import com.comarch.fiberBilling.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = userMapper.userListToUserDtosList(users);
        return ResponseEntity.ok(userDTOs);
    }

    @Override
    public ResponseEntity getUserById(String userId) {
        Long id;
        try {
            id = Long.valueOf(userId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        UserDTO userDTO = userMapper.userToUserDto(user.get());

        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity addUser(UserDTO userDTO) {
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User data not provided");
        }

        User user = userMapper.userDtoToUser(userDTO);

        User newUser = userRepository.save(user);
        UserDTO newUserDTO = userMapper.userToUserDto(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }
}
