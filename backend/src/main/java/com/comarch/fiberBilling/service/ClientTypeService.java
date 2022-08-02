package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientTypeService {
    ResponseEntity<List<ClientTypeDTO>> getAll();

    ResponseEntity getClientTypeById(String clientTypeId);

    ResponseEntity getClientTypeByName(String clientTypeName);

    ResponseEntity addClientType(ClientTypeDTO clientTypeDTO);

    ResponseEntity changeClientType(String clientTypeId, ClientTypeDTO clientTypeDTO);

    ResponseEntity deleteClientTypeById(String clientTypeId);

    ResponseEntity getClientTypeByLogin(String clientLogin);
}
