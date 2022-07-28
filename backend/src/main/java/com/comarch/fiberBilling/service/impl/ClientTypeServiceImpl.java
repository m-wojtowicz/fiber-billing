package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.ClientTypeMapper;
import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.entity.ClientType;
import com.comarch.fiberBilling.repository.ClientTypeRepository;
import com.comarch.fiberBilling.service.ClientTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientTypeServiceImpl implements ClientTypeService {

    private final ClientTypeRepository clientTypeRepository;
    private final ClientTypeMapper clientTypeMapper = ClientTypeMapper.INSTANCE;

    @Override
    public ResponseEntity<List<ClientTypeDTO>> getAll() {
        List<ClientType> clientTypes = clientTypeRepository.findAll();
        List<ClientTypeDTO> clientTypeDTOs = clientTypeMapper.clientTypesListToClientTypesDtosList(clientTypes);
        return ResponseEntity.ok(clientTypeDTOs);
    }

    @Override
    public ResponseEntity getClientTypeById(String clientTypeId) {
        Long id;
        try {
            id = Long.valueOf(clientTypeId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientType> clientType = clientTypeRepository.findById(id);
        if (clientType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        ClientTypeDTO clientTypeDTO = clientTypeMapper.clientTypeToClientTypeDto(clientType.get());

        return ResponseEntity.ok(clientTypeDTO);
    }

    @Override
    public ResponseEntity getClientTypeByName(String clientTypeName) {
        List<ClientType> clientType = clientTypeRepository.findByType(clientTypeName);
        if (clientType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClientType not found");
        }

        ClientTypeDTO clientTypeDTO = clientTypeMapper.clientTypeToClientTypeDto(clientType.get(0));
        return ResponseEntity.ok(clientTypeDTO);
    }
}
