package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<UserDTO>> getAll();

    ResponseEntity getUserById(String userId);

    ResponseEntity addUser(UserDTO userDTO);
}
