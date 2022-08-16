package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.entity.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<Order>> getUserOrders(String userId);

    ResponseEntity<Order> getOrder(String orderId);

    ResponseEntity<Order> addOrder(String orderId);
}
