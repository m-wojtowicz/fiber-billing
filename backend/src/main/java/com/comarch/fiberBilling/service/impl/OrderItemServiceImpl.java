package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderItemMapper;
import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.repository.OrderItemRepository;
import com.comarch.fiberBilling.repository.OrderRepository;
import com.comarch.fiberBilling.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;


    @Override
    public ResponseEntity getOrderItem(String orderItemId) {
        Long id;
        try {
            id = Long.valueOf(orderItemId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        OrderItemDTO orderItemDTO = orderItemMapper.orderItemToOrderItemDto(orderItem.get());
        return ResponseEntity.ok(orderItemDTO);
    }

    @Override
    public ResponseEntity getOrderItems(String orderId) {
        Long id;
        try {
            id = Long.valueOf(orderId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<OrderItem> orderItems = orderItemRepository.findByOrder(order.get());
        List<OrderItemDTO> orderItemsDTOS = orderItemMapper.orderItemsListToOrderItemsDtosList(orderItems);
        return ResponseEntity.ok(orderItemsDTOS);
    }

    @Override
    public ResponseEntity addOrderItem(OrderItemDTO orderItemDTO) {
        if (orderItemDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OrderItem not provided");
        }
        OrderItem orderItem = orderItemMapper.orderItemDtoToOrderItem(orderItemDTO);
        OrderItem newOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrderItem.getId());
    }

    @Override
    public ResponseEntity changeOrderItem(String orderItemId, OrderItemDTO orderItemDTO) {
        Long id;
        try {
            id = Long.valueOf(orderItemId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        OrderItem newOrderItem = orderItemMapper.orderItemDtoToOrderItem(orderItemDTO);
        newOrderItem.setId(orderItem.get().getId());
        orderItemRepository.save(newOrderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemMapper.orderItemDtoToOrderItem(orderItemDTO));
    }

    @Override
    public ResponseEntity deleteOrderItem(String orderItemId) {
        Long id;
        try {
            id = Long.valueOf(orderItemId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        orderItemRepository.delete(orderItem.get());
        return ResponseEntity.status(HttpStatus.OK).body(orderItemMapper.orderItemToOrderItemDto(orderItem.get()));
    }
}
