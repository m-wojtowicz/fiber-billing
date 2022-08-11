package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import org.springframework.http.ResponseEntity;

public interface OrderItemService {
    ResponseEntity getOrderItem(String orderItemId);

    ResponseEntity getOrderItems(String orderId);

    ResponseEntity addOrderItem(OrderItemDTO orderItemDTO);

    ResponseEntity changeOrderItem(String orderItemId, OrderItemDTO orderItemDTO);

    ResponseEntity deleteOrderItem(String orderItemId);
}
