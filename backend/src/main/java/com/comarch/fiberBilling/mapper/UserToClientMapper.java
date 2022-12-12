package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.api.request.PutUserData;
import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientData;

public class UserToClientMapper {

    public static ClientData UserToClient(PutUserData putUserData, ClientData clientData) {
        Address newAddress = clientData.getAddress();
        newAddress.setCountry(putUserData.getCountry());
        newAddress.setCity(putUserData.getCity());
        newAddress.setStreet(putUserData.getStreet());
        newAddress.setStreetNumber(putUserData.getStreetNumber());
        newAddress.setHouseNumber(putUserData.getHouseNumber());
        newAddress.setZipCode(putUserData.getZipCode());
        newAddress.setPostOffice(putUserData.getPostOffice());
        clientData.setName(putUserData.getName());
        clientData.setSurname(putUserData.getSurname());
        clientData.setEmailAddress(putUserData.getEmail());
        clientData.setAddress(newAddress);
        return clientData;
    }
}
