package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ClientTypeRepositoryTest {

    @Autowired
    private ClientTypeRepository clientTypeRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveClientTypeTest(){
        ClientType clientType = ClientType.builder()
                .id(1L)
                .type("home")
                .build();

        clientTypeRepository.save(clientType);

        Assertions.assertThat(clientType.getId()).isGreaterThan(0);


    }

    @Test
    @Order(2)
    public void getClientTypeTest(){

        ClientType clientType = clientTypeRepository.findById(1L).get();

        Assertions.assertThat(clientType.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfClientTypesTest(){

        List<ClientType> clientTypes = clientTypeRepository.findAll();

        Assertions.assertThat(clientTypes.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateClientTypeTest(){

        ClientType clientType = clientTypeRepository.findById(1L).get();

        clientType.setType("Individual");
        ClientType clientTypeUpdated = clientTypeRepository.save(clientType);

        Assertions.assertThat(clientTypeUpdated.getType()).isEqualTo("Individual");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteClientTypeTest(){

        ClientType clientType = clientTypeRepository.findById(1L).get();
        clientTypeRepository.delete(clientType);
        ClientType clientType1 = null;
        Optional<ClientType> optionalClientType = clientTypeRepository.findById(1L);

        if(optionalClientType.isPresent()){
            clientType1 = optionalClientType.get();
        }

        Assertions.assertThat(clientType1).isNull();
    }
}
