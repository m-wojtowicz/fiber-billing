package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.entity.Address;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.ClientType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:24:25+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class ClientDataMapperImpl implements ClientDataMapper {

    @Override
    public ClientDataDTO clientDataToClientDataDto(ClientData clientData) {
        if ( clientData == null ) {
            return null;
        }

        ClientDataDTO.ClientDataDTOBuilder clientDataDTO = ClientDataDTO.builder();

        clientDataDTO.id( clientData.getId() );
        clientDataDTO.name( clientData.getName() );
        clientDataDTO.surname( clientData.getSurname() );
        clientDataDTO.emailAddress( clientData.getEmailAddress() );
        clientDataDTO.address( addressToAddressDTO( clientData.getAddress() ) );
        clientDataDTO.login( clientData.getLogin() );
        clientDataDTO.clientType( clientTypeToClientTypeDTO( clientData.getClientType() ) );

        return clientDataDTO.build();
    }

    @Override
    public ClientData clientDataDtoToClientData(ClientDataDTO clientDataDTO) {
        if ( clientDataDTO == null ) {
            return null;
        }

        ClientData.ClientDataBuilder clientData = ClientData.builder();

        clientData.id( clientDataDTO.getId() );
        clientData.name( clientDataDTO.getName() );
        clientData.surname( clientDataDTO.getSurname() );
        clientData.emailAddress( clientDataDTO.getEmailAddress() );
        clientData.address( addressDTOToAddress( clientDataDTO.getAddress() ) );
        clientData.login( clientDataDTO.getLogin() );
        clientData.clientType( clientTypeDTOToClientType( clientDataDTO.getClientType() ) );

        return clientData.build();
    }

    @Override
    public List<ClientDataDTO> clientDataListToClientDataDtosList(List<ClientData> clientData) {
        if ( clientData == null ) {
            return null;
        }

        List<ClientDataDTO> list = new ArrayList<ClientDataDTO>( clientData.size() );
        for ( ClientData clientData1 : clientData ) {
            list.add( clientDataToClientDataDto( clientData1 ) );
        }

        return list;
    }

    @Override
    public List<ClientData> clientDataDtosListToClientDataList(List<ClientDataDTO> clientDataDTOs) {
        if ( clientDataDTOs == null ) {
            return null;
        }

        List<ClientData> list = new ArrayList<ClientData>( clientDataDTOs.size() );
        for ( ClientDataDTO clientDataDTO : clientDataDTOs ) {
            list.add( clientDataDtoToClientData( clientDataDTO ) );
        }

        return list;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
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

    protected ClientTypeDTO clientTypeToClientTypeDTO(ClientType clientType) {
        if ( clientType == null ) {
            return null;
        }

        ClientTypeDTO.ClientTypeDTOBuilder clientTypeDTO = ClientTypeDTO.builder();

        clientTypeDTO.id( clientType.getId() );
        clientTypeDTO.type( clientType.getType() );

        return clientTypeDTO.build();
    }

    protected Address addressDTOToAddress(AddressDTO addressDTO) {
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

    protected ClientType clientTypeDTOToClientType(ClientTypeDTO clientTypeDTO) {
        if ( clientTypeDTO == null ) {
            return null;
        }

        ClientType.ClientTypeBuilder clientType = ClientType.builder();

        clientType.id( clientTypeDTO.getId() );
        clientType.type( clientTypeDTO.getType() );

        return clientType.build();
    }
}
