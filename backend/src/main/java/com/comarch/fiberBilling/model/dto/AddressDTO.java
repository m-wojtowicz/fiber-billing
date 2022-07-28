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
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;
    private String postOffice;
    private String city;
}
