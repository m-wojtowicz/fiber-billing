package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.repository.OrderItemRepository;
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

    private final OrderItemRepository orderItemRepository;

    @Override
    public ResponseEntity getAllUserProducts(Long id) {
        List<OrderItem> orderItemList = orderItemRepository.getAllUserProducts(id).orElse(null);
        if(orderItemList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
        }
        return ResponseEntity.ok(orderItemList);
    }
}
