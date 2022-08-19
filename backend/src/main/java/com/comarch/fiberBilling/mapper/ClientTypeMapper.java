package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.model.entity.ClientType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientTypeMapper {
    ClientTypeMapper INSTANCE = Mappers.getMapper(ClientTypeMapper.class);

    ClientTypeDTO clientTypeToClientTypeDto(ClientType clientType);

    ClientType clientTypeDtoToClientType(ClientTypeDTO clientTypeDTO);

    List<ClientTypeDTO> clientTypesListToClientTypesDtosList(List<ClientType> clientTypes);

    List<ClientType> clientTypesDtosListToClientTypesList(List<ClientTypeDTO> clientTypesDTOs);
}
