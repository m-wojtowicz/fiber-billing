package com.comarch.fiberBilling.mapper;

import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDTO orderDTO);

    List<OrderDTO> ordersListToOrdersDtosList(List<Order> orders);

    List<Order> ordersDtosListToOrdersList(List<OrderDTO> orderDTOs);
}
