package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.ClientTypeMapper;
import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.ClientType;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.repository.ClientTypeRepository;
import com.comarch.fiberBilling.service.ClientTypeService;
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
public class ClientTypeServiceImpl implements ClientTypeService {

    private final ClientTypeRepository clientTypeRepository;
    private final ClientDataRepository clientDataRepository;

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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
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

    @Override
    @Transactional
    public ResponseEntity addClientType(ClientTypeDTO clientTypeDTO) {
        if (clientTypeDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client data not provided");
        }
        ClientType clientType = clientTypeMapper.clientTypeDtoToClientType(clientTypeDTO);
        ClientType newClientType = clientTypeRepository.save(clientType);
        ClientTypeDTO newClientTypeDTO = clientTypeMapper.clientTypeToClientTypeDto(newClientType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClientTypeDTO);
    }

    @Override
    @Transactional
    public ResponseEntity changeClientType(String clientTypeId, ClientTypeDTO clientTypeDTO) {
        Long id;
        try {
            id = Long.valueOf(clientTypeId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientType> clientType = clientTypeRepository.findById(id);
        if (clientType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        ClientType newClientType = clientTypeMapper.clientTypeDtoToClientType(clientTypeDTO);
        newClientType.setId(clientType.get().getId());
        clientTypeRepository.save(newClientType);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientTypeMapper.clientTypeToClientTypeDto(newClientType));
    }

    @Override
    @Transactional
    public ResponseEntity deleteClientTypeById(String clientTypeId) {
        Long id;
        try {
            id = Long.valueOf(clientTypeId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientType> clientType = clientTypeRepository.findById(id);
        if (clientType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        clientTypeRepository.delete(clientType.get());
        return ResponseEntity.status(HttpStatus.OK).body(clientTypeMapper.clientTypeToClientTypeDto(clientType.get()));
    }

    @Override
    @Transactional
    public ResponseEntity getClientTypeByLogin(String clientLogin) {
        ClientData clientData = clientDataRepository.findByLogin(clientLogin).orElse(null);
        if (clientData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Data not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientData.getClientType().getType());
    }
}
