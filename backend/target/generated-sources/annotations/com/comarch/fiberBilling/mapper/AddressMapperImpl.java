package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.entity.Address;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:24:26+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.id( address.getId() );
        addressDTO.country( address.getCountry() );
        addressDTO.city( address.getCity() );
        addressDTO.street( address.getStreet() );
        addressDTO.streetNumber( address.getStreetNumber() );
        addressDTO.houseNumber( address.getHouseNumber() );
        addressDTO.zipCode( address.getZipCode() );
        addressDTO.postOffice( address.getPostOffice() );

        return addressDTO.build();
    }

    @Override
    public Address addressDtoToAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.id( addressDTO.getId() );
        address.country( addressDTO.getCountry() );
        address.city( addressDTO.getCity() );
        address.street( addressDTO.getStreet() );
        address.streetNumber( addressDTO.getStreetNumber() );
        address.houseNumber( addressDTO.getHouseNumber() );
        address.zipCode( addressDTO.getZipCode() );
        address.postOffice( addressDTO.getPostOffice() );

        return address.build();
    }

    @Override
    public List<AddressDTO> addressesListToAddressesDtosList(List<Address> addresses) {
        if ( addresses == null ) {
            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>( addresses.size() );
        for ( Address address : addresses ) {
            list.add( addressToAddressDto( address ) );
        }

        return list;
    }

    @Override
    public List<Address> addressesDtosListToAddressesList(List<AddressDTO> addressesDTOs) {
        if ( addressesDTOs == null ) {
            return null;
        }

        List<Address> list = new ArrayList<Address>( addressesDTOs.size() );
        for ( AddressDTO addressDTO : addressesDTOs ) {
            list.add( addressDtoToAddress( addressDTO ) );
        }

        return list;
    }
}
