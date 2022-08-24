package com.comarch.fiberBilling.service;

import com.comarch.fiberBilling.model.api.response.GetAllProductParameters;
import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.model.entity.OrderItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderItemService {
    public ResponseEntity<List<OrderItem>> getAllUserProducts(Long id, String userType);

    public ResponseEntity<List<GetAllProductParameters>> getAllProductParameters(Long id);

    public ResponseEntity<List<OrderItemDTO>> getOrderItems(String orderId);

    public ResponseEntity addOffer(String orderId, String offerId);

    public ResponseEntity removeItem(String itemId);
}
