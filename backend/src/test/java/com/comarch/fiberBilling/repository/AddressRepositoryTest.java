package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.repository.AddressRepository;
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

public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    //Junit test for saveAddress
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAddressTest(){

        Address address = Address.builder()
                .city("Wrocław")
                .country("Poland")
                .houseNumber("11")
                .street("Kolorowa")
                .streetNumber("33")
                .zipCode("53-655")
                .postOffice("Wrocław")
                .build();

        addressRepository.save(address);

       Assertions.assertThat(address.getId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public void getAddressTest(){

        Address address = addressRepository.findById(1L).get();

        Assertions.assertThat(address.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfAddressesTest(){

        List<Address> addresses = addressRepository.findAll();

        Assertions.assertThat(addresses.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAddressTest(){

        Address address = addressRepository.findById(1L).get();

        address.setCity("Warszawa");
        Address addressUpdated = addressRepository.save(address);

        Assertions.assertThat(addressUpdated.getCity()).isEqualTo("Warszawa");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAddressTest(){

        Address address = addressRepository.findById(1L).get();
        addressRepository.delete(address);
        Address address1 = null;
        Optional<Address> optionalAddress = addressRepository.findById(1L);

        if(optionalAddress.isPresent()){
            address1 = optionalAddress.get();
        }

        Assertions.assertThat(address1).isNull();
    }

}
