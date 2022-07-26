package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.model.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDTO addressDTO);

    List<AddressDTO> addressesListToAddressesDtosList(List<Address> addresses);

    List<Address> addressesDtosListToAddressesList(List<AddressDTO> addressesDTOs);
}
