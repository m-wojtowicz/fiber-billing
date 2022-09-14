package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.api.request.PutConfigItems;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.Order;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity getUserOrders(String userId, int pageNo, String filter, String filterType);

    ResponseEntity getOrder(String orderId);

    ResponseEntity addOrder(OrderDTO orderDTO);

    ResponseEntity changeOrder(String orderId, OrderDTO orderDTO);

    ResponseEntity deleteOrder(String orderId);

    Order createOrder(String userId);

    ResponseEntity changeStatus(String orderId, String status);

    ResponseEntity getOpenOrder(String userId);

    ResponseEntity getConfigItems(String orderId, String clientType);

    ResponseEntity putConfigItems(String orderId, PutConfigItems data);
}
