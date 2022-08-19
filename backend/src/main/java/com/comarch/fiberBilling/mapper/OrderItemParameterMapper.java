package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.OrderItemParameterDTO;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemParameterMapper {
    OrderItemParameterMapper INSTANCE = Mappers.getMapper(OrderItemParameterMapper.class);

    OrderItemParameterDTO entityToDto(OrderItemParameter orderItemParameter);

    OrderItemParameter dtoToEntity(OrderItemParameterDTO orderItemParameterDTO);

    List<OrderItemParameterDTO> entityListToDtoList(List<OrderItemParameter> orderItemParameters);

    List<OrderItemParameter> dtoListToEntityList(List<OrderItemParameterDTO> orderItemParameterDTOS);
}
