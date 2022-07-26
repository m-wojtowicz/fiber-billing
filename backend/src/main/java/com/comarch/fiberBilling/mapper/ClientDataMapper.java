package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientDataMapper {
    ClientDataMapper INSTANCE = Mappers.getMapper(ClientDataMapper.class);

    ClientDataDTO clientDataToClientDataDto(ClientData clientData);

    ClientData clientDataDtoToClientData(ClientDataDTO clientDataDTO);

    List<ClientDataDTO> clientDataListToClientDataDtosList(List<ClientData> clientData);

    List<ClientData> clientDataDtosListToClientDataList(List<ClientDataDTO> clientDataDTOs);
}