package com.comarch.fiberBilling.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutUserData {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String country;
    private String city;
    private String street;
    private String streetNumber;
    private String houseNumber;
    private String zipCode;
    private String postOffice;
}
