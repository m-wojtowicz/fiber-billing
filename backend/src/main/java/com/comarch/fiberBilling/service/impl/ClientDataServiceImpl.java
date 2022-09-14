package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.ClientDataMapper;
import com.comarch.fiberBilling.mapper.UserToClientMapper;
import com.comarch.fiberBilling.model.api.request.PutUserData;
import com.comarch.fiberBilling.model.api.response.GetUserData;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.service.ClientDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
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
        clientData.setLogin(clientData.getLogin().toLowerCase(Locale.ROOT));
        ClientData newClientData = clientDataRepository.save(clientData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClientData.getId());
    }

    @Override
    @Transactional
    public ResponseEntity changeClientData(String clientDataId, PutUserData putUserData) {
        Long id;
        try {
            id = Long.valueOf(clientDataId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        ClientData clientData = clientDataRepository.findById(id).orElse(null);
        if (clientData == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        putUserData.setId(id);
        ClientData newClientData = UserToClientMapper.UserToClient(putUserData, clientData);
        clientDataRepository.save(newClientData);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDataMapper.clientDataToClientDataDto(newClientData));
    }

    @Override
    @Transactional
    public ResponseEntity deleteClientDataById(String clientDataId) {
        Long id;
        try {
            id = Long.valueOf(clientDataId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        clientDataRepository.delete(clientData.get());
        return ResponseEntity.status(HttpStatus.OK).body(clientDataMapper.clientDataToClientDataDto(clientData.get()));
    }

    @Override
    @Transactional
    public ResponseEntity getClientDataByLogin(String login) {
        Optional<ClientData> clientData = clientDataRepository.findByLogin(login);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        GetUserData userData = GetUserData.builder()
                .id(clientData.get().getId())
                .name(clientData.get().getName())
                .surname(clientData.get().getSurname())
                .email(clientData.get().getEmailAddress())
                .country(clientData.get().getAddress().getCountry())
                .city(clientData.get().getAddress().getCity())
                .street(clientData.get().getAddress().getStreet())
                .streetNumber(clientData.get().getAddress().getStreetNumber())
                .houseNumber(clientData.get().getAddress().getHouseNumber())
                .zipCode(clientData.get().getAddress().getZipCode())
                .postOffice(clientData.get().getAddress().getPostOffice())
                .build();
        return ResponseEntity.ok(userData);
    }
}
