package com.comarch.fiberBilling.repository;



import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.ClientType;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ClientDataRepositoryTest {

    @Autowired
    private ClientTypeRepository clientTypeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientDataRepository clientDataRepository;
    Address newAddress = Address.builder()
            .country("Poland")
            .city("Wroclaw")
            .country("Poland")
            .houseNumber("12")
            .street("swojska")
            .streetNumber("33")
            .zipCode("53-305")
            .postOffice("Wroclaw")
            .build();

    Address newAddress2 = Address.builder()
            .id(1L)
            .build();


    ClientType newClientType = ClientType.builder()
            .type("regular")
            .build();

    ClientType newClientType2 = ClientType.builder()
            .id(1L)
            .build();

    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveClientDataTest(){
        clientTypeRepository.save(newClientType);
        addressRepository.save(newAddress);

        ClientData clientData = ClientData.builder()
                .emailAddress("messi@better.thenme")
                .login("CR7")
                .name("Cristian")
                .surname("Baran")
                .address(newAddress2)
                .clientType(newClientType2)
                .build();

        clientDataRepository.save(clientData);
        Assertions.assertThat(clientData.getId()).isGreaterThan(0);


    }

    @Test
    @Order(2)
    public void getClientDataTest(){
        ClientData clientData = clientDataRepository.findById(1L).get();
        Assertions.assertThat(clientData.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfClientDateTest(){
        List<ClientData> clientDate = clientDataRepository.findAll();
        Assertions.assertThat(clientDate.size()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateClientDataTest(){
        ClientData clientData =clientDataRepository.findById(1L).get();
        clientData.setLogin("Leo30");
        ClientData clientDataUpdated = clientDataRepository.save(clientData);
        Assertions.assertThat(clientDataUpdated.getLogin()).isEqualTo("Leo30");
    }

    @Test
    @Order(5)
    @Rollback(value = false)

    public void deleteClientDataTest() {
        ClientData clientData = clientDataRepository.findById(1L).get();
        clientDataRepository.delete(clientData);
        ClientData clientData1 = null;
        Optional<ClientData> optionalClientData = clientDataRepository.findById(1L);

        if (optionalClientData.isPresent()) {
            clientData1 = optionalClientData.get();
        }
    }
}
