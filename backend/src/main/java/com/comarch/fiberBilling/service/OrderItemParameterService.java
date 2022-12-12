package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.dto.OrderItemParameterDTO;
import org.springframework.http.ResponseEntity;

public interface OrderItemParameterService {
    ResponseEntity getOrderItemParameter(String orderItemParameterId);

    ResponseEntity getOrderItemParameters(String orderItemId);

    ResponseEntity addOrderItemParameter(OrderItemParameterDTO orderItemParameterDTO);

    ResponseEntity changeOrderItemParameter(String orderItemId, OrderItemParameterDTO orderItemParameterDTO);

    ResponseEntity deleteOrderItemParameter(String orderItemParameterId);
}
