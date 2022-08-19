package com.comarch.fiberBilling.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private long id;
    private String country;
    private String city;
    private String street;
    private String streetNumber;
    private String houseNumber;
    private String zipCode;
    private String postOffice;
}
