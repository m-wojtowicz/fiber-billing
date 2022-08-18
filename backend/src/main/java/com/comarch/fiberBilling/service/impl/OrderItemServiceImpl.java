package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.model.api.response.GetAllProductParameters;
import com.comarch.fiberBilling.model.api.response.GetAllUserProducts;
import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import com.comarch.fiberBilling.repository.OrderItemParameterRepository;
import com.comarch.fiberBilling.repository.OrderItemRepository;
import com.comarch.fiberBilling.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;

    @Override
    public ResponseEntity getAllUserProducts(Long id, String userType) {
        List<OrderItem> orderItemList = orderItemRepository.getAllUserProducts(id).orElse(null);
        if (orderItemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
        }
        List<GetAllUserProducts> allUserProducts = new ArrayList<>();
        orderItemList.forEach(x -> {
            BigDecimal cost = new BigDecimal("0");
            List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(x);
            if (Objects.equals(userType, "regular")) {
                for (var orderItemParameter : orderItemParameters) {
                    cost = cost.add(orderItemParameter.getParameterDetail().getPriceRegular());
                }
            } else {
                for (var orderItemParameter : orderItemParameters) {
                    cost = cost.add(orderItemParameter.getParameterDetail().getPriceBusiness());
                }
            }
            allUserProducts.add(GetAllUserProducts.builder()
                    .id(x.getId())
                    .orderItemName(x.getOrderItemName())
                    .activationDate(x.getActivationDate())
                    .status(x.getStatus())
                    .cost(cost)
                    .build());
        });
        return ResponseEntity.ok(allUserProducts);
    }

    @Override
    public ResponseEntity getAllProductParameters(Long orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(orderItem.get());
        List<GetAllProductParameters> getAllProductParameters = new ArrayList<>();
        orderItemParameters.forEach(x -> {
            getAllProductParameters.add(GetAllProductParameters.builder()
                    .id(x.getId())
                    .orderItemName(x.getOrderItem().getOrderItemName())
                    .name(x.getParameterDetail().getParameter().getName())
                    .value(x.getParameterDetail().getValue())
                    .priceBusiness(x.getParameterDetail().getPriceBusiness())
                    .priceRegular(x.getParameterDetail().getPriceRegular())
                    .build());
        });
        return ResponseEntity.ok(getAllProductParameters);
    }
}