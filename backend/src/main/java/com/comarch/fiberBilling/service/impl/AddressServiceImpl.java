package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.AddressMapper;
import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.repository.AddressRepository;
import com.comarch.fiberBilling.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Override
    public ResponseEntity<List<AddressDTO>> getAll() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressesDTOs = addressMapper.addressesListToAddressesDtosList(addresses);
        return ResponseEntity.ok(addressesDTOs);
    }

    @Override
    public ResponseEntity getAddressById(String addressId) {
        Long id;
        try {
            id = Long.valueOf(addressId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        AddressDTO addressDTO = addressMapper.addressToAddressDto(address.get());
        return ResponseEntity.ok(addressDTO);
    }

    @Override
    @Transactional
    public ResponseEntity addAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address data not provided");
        }
        Address address = addressMapper.addressDtoToAddress(addressDTO);
        Address newAddress = addressRepository.save(address);
        AddressDTO newAddressDTO = addressMapper.addressToAddressDto(newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAddressDTO);
    }

    @Override
    @Transactional
    public ResponseEntity changeAddress(String addressId, AddressDTO addressDTO) {
        Long id;
        try {
            id = Long.valueOf(addressId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        Address newAddress = addressMapper.addressDtoToAddress(addressDTO);
        newAddress.setId(address.get().getId());
        addressRepository.save(newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressMapper.addressToAddressDto(newAddress));
    }

    @Override
    @Transactional
    public ResponseEntity deleteAddressById(String addressId) {
        Long id;
        try {
            id = Long.valueOf(addressId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        addressRepository.delete(address.get());
        return ResponseEntity.status(HttpStatus.OK).body(addressMapper.addressToAddressDto(address.get()));
    }
}
