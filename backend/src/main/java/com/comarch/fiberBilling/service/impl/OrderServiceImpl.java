package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderMapper;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.repository.OrderRepository;
import com.comarch.fiberBilling.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ClientDataRepository clientDataRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Override
    public ResponseEntity getUserOrders(String userId) {
        Long id;
        try {
            id = Long.valueOf(userId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<Order> orders = orderRepository.findByClientData(clientData.get());
        List<OrderDTO> orderDTOS = orderMapper.ordersListToOrdersDtosList(orders);
        return ResponseEntity.ok(orderDTOS);
    }

    @Override
    public ResponseEntity getOrder(String orderId) {
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

        OrderDTO orderDTO = orderMapper.orderToOrderDto(order.get());
        return ResponseEntity.ok(orderDTO);
    }

    @Override
    @Transactional
    public ResponseEntity addOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not provided");
        }
        Order order = orderMapper.orderDtoToOrder(orderDTO);
        Order newOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder.getId());
    }

    @Override
    @Transactional
    public ResponseEntity changeOrder(String orderId, OrderDTO orderDTO) {
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
        Order newOrder = orderMapper.orderDtoToOrder(orderDTO);
        newOrder.setId(order.get().getId());
        orderRepository.save(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToOrderDto(newOrder));
    }

    @Override
    @Transactional
    public ResponseEntity deleteOrder(String orderId) {
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
        orderRepository.delete(order.get());
        return ResponseEntity.status(HttpStatus.OK).body(orderMapper.orderToOrderDto(order.get()));
    }
}
