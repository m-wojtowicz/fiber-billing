package com.comarch.fiberBilling.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDataDTO {

    private long id;
    private String name;
    private String surname;
    private int pesel;
    private String companyName;
    private int taxId;
    private String emailAddress;
    private int phoneNumber;
    private AddressDTO address;
    private UserDTO user;
}