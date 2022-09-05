package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.api.request.PutConfigItems;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity getUserOrders(String userId, int pageNo, String filter);

    ResponseEntity getOrder(String orderId);

    ResponseEntity addOrder(OrderDTO orderDTO);

    ResponseEntity changeOrder(String orderId, OrderDTO orderDTO);

    ResponseEntity deleteOrder(String orderId);

    ResponseEntity createOrder(String userId, String businessKey);

    ResponseEntity changeStatus(String orderId, String status);

    ResponseEntity getOpenOrder(String userId);

    ResponseEntity getConfigItems(String orderId, String clientType);

    ResponseEntity putConfigItems(String orderId, PutConfigItems data);
}
