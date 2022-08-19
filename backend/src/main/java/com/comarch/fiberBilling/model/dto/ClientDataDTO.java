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
    private String emailAddress;
    private AddressDTO address;
    private String login;
    private ClientTypeDTO clientType;
}
