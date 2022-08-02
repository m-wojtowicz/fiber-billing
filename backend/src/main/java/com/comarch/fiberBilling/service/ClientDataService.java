package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.api.request.PutUserData;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientDataService {
    ResponseEntity<List<ClientDataDTO>> getAll();

    ResponseEntity getClientDataById(String clientDataId);

    ResponseEntity addClientData(ClientDataDTO clientDataDTO);

    ResponseEntity changeClientData(String clientDataId, PutUserData userData);

    ResponseEntity deleteClientDataById(String clientDataId);

    ResponseEntity getClientDataByLogin(String clientDataLogin);
}
