package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.ClientDataMapper;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.service.ClientDataService;
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
public class ClientDataServiceImpl implements ClientDataService {

    private final ClientDataRepository clientDataRepository;
    private final ClientDataMapper clientDataMapper = ClientDataMapper.INSTANCE;

    @Override
    public ResponseEntity<List<ClientDataDTO>> getAll() {
        List<ClientData> clientData = clientDataRepository.findAll();
        List<ClientDataDTO> clientDataDTOs = clientDataMapper.clientDataListToClientDataDtosList(clientData);
        return ResponseEntity.ok(clientDataDTOs);
    }

    @Override
    public ResponseEntity getClientDataById(String clientDataId) {
        Long id;
        try {
            id = Long.valueOf(clientDataId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }

        ClientDataDTO clientDataDTO = clientDataMapper.clientDataToClientDataDto(clientData.get());

        return ResponseEntity.ok(clientDataDTO);
    }

    @Override
    @Transactional
    public ResponseEntity addClientData(ClientDataDTO clientDataDTO) {
        if (clientDataDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client data not provided");
        }

        ClientData clientData = clientDataMapper.clientDataDtoToClientData(clientDataDTO);

        ClientData newClientData = clientDataRepository.save(clientData);
        ClientDataDTO newClientDataDto = clientDataMapper.clientDataToClientDataDto(newClientData);

        return ResponseEntity.status(HttpStatus.CREATED).body(newClientDataDto);
    }
}
