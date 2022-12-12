package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.entity.ClientType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T12:24:26+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class ClientTypeMapperImpl implements ClientTypeMapper {

    @Override
    public ClientTypeDTO clientTypeToClientTypeDto(ClientType clientType) {
        if ( clientType == null ) {
            return null;
        }

        ClientTypeDTO.ClientTypeDTOBuilder clientTypeDTO = ClientTypeDTO.builder();

        clientTypeDTO.id( clientType.getId() );
        clientTypeDTO.type( clientType.getType() );

        return clientTypeDTO.build();
    }

    @Override
    public ClientType clientTypeDtoToClientType(ClientTypeDTO clientTypeDTO) {
        if ( clientTypeDTO == null ) {
            return null;
        }

        ClientType.ClientTypeBuilder clientType = ClientType.builder();

        clientType.id( clientTypeDTO.getId() );
        clientType.type( clientTypeDTO.getType() );

        return clientType.build();
    }

    @Override
    public List<ClientTypeDTO> clientTypesListToClientTypesDtosList(List<ClientType> clientTypes) {
        if ( clientTypes == null ) {
            return null;
        }

        List<ClientTypeDTO> list = new ArrayList<ClientTypeDTO>( clientTypes.size() );
        for ( ClientType clientType : clientTypes ) {
            list.add( clientTypeToClientTypeDto( clientType ) );
        }

        return list;
    }

    @Override
    public List<ClientType> clientTypesDtosListToClientTypesList(List<ClientTypeDTO> clientTypesDTOs) {
        if ( clientTypesDTOs == null ) {
            return null;
        }

        List<ClientType> list = new ArrayList<ClientType>( clientTypesDTOs.size() );
        for ( ClientTypeDTO clientTypeDTO : clientTypesDTOs ) {
            list.add( clientTypeDtoToClientType( clientTypeDTO ) );
        }

        return list;
    }
}
