package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO orderItemToOrderItemDto(OrderItem orderItem);

    OrderItem orderItemDtoToOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> orderItemsListToOrderItemsDtosList(List<OrderItem> orderItems);

    List<OrderItem> orderItemsDtosListToOrderItemsList(List<OrderItemDTO> orderItemDTOS);
}
