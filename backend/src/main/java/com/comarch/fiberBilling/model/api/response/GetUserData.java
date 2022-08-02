package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserData {
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
