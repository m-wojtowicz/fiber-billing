package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    ResponseEntity<List<AddressDTO>> getAll();

    ResponseEntity getAddressById(String addressId);

    ResponseEntity addAddress(AddressDTO addressDTO);
}
