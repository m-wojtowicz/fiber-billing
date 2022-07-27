package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientTypeService {
    ResponseEntity<List<ClientTypeDTO>> getAll();

    ResponseEntity getClientTypeById(String clientTypeId);
}