package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.entity.OrderItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderItemService {
    public ResponseEntity<List<OrderItem>> getAllUserProducts(Long id);
}
