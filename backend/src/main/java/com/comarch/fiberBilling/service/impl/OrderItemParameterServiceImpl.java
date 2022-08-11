package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderItemParameterMapper;
import com.comarch.fiberBilling.model.dto.OrderItemParameterDTO;
import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import com.comarch.fiberBilling.repository.OrderItemParameterRepository;
import com.comarch.fiberBilling.repository.OrderItemRepository;
import com.comarch.fiberBilling.service.OrderItemParameterService;
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
public class OrderItemParameterServiceImpl implements OrderItemParameterService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;
    private final OrderItemParameterMapper orderItemParameterMapper = OrderItemParameterMapper.INSTANCE;

    @Override
    public ResponseEntity getOrderItemParameter(String orderItemParameterId) {
        Long id;
        try {
            id = Long.valueOf(orderItemParameterId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItemParameter> orderItemParameter = orderItemParameterRepository.findById(id);
        if (orderItemParameter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        OrderItemParameterDTO orderItemParameterDTO = orderItemParameterMapper.entityToDto(orderItemParameter.get());
        return ResponseEntity.ok(orderItemParameterDTO);
    }

    @Override
    public ResponseEntity getOrderItemParameters(String orderItemId) {
        Long id;
        try {
            id = Long.valueOf(orderItemId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(orderItem.get());
        List<OrderItemParameterDTO> orderItemParameterDTOS = orderItemParameterMapper.entityListToDtoList(orderItemParameters);
        return ResponseEntity.ok(orderItemParameterDTOS);
    }

    @Override
    public ResponseEntity addOrderItemParameter(OrderItemParameterDTO orderItemParameterDTO) {
        return null;
    }

    @Override
    public ResponseEntity changeOrderItemParameter(String orderItemId, OrderItemParameterDTO orderItemParameterDTO) {
        return null;
    }

    @Override
    public ResponseEntity deleteOrderItemParameter(String orderItemParameterId) {
        return null;
    }


}
