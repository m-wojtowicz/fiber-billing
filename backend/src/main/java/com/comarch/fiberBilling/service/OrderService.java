package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity getUserOrders(String userId);

    ResponseEntity getOrder(String orderId);

    ResponseEntity addOrder(OrderDTO orderDTO);

    ResponseEntity changeOrder(String orderId, OrderDTO orderDTO);

    ResponseEntity deleteOrder(String orderId);

    ResponseEntity createOrder(String userId);

    ResponseEntity changeStatus(String orderId, String status);
}
