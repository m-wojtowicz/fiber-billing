package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderItemMapper;
import com.comarch.fiberBilling.model.api.response.GetAllOrderItems;
import com.comarch.fiberBilling.model.api.response.GetAllProductParameters;
import com.comarch.fiberBilling.model.api.response.GetAllUserProducts;
import com.comarch.fiberBilling.model.entity.*;
import com.comarch.fiberBilling.repository.*;
import com.comarch.fiberBilling.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;
    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;

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
                    if (!orderItemParameter.getParameterDetail().isMonthly())
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

    @Override
    public ResponseEntity getOrderItems(String orderId) {
        long id;
        try {
            id = Long.parseLong(orderId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<OrderItem> orderItems = orderItemRepository.findByOrder(order.get());
        List<GetAllOrderItems> orderItemsList = new ArrayList<>();
        orderItems.forEach(o -> {
            orderItemsList.add(GetAllOrderItems.builder().
                    id(o.getId()).
                    name(o.getOrderItemName()).
                    activationDate(o.getActivationDate()).
                    status(o.getStatus()).
                    build());
        });
        return ResponseEntity.ok(orderItemsList);
    }

    @Override
    @Transactional
    public ResponseEntity addOffer(String orderId, String offerId) {
        long id;
        try {
            id = Long.parseLong(orderId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order ID NaN");
        }
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order ID not found");
        }

        try {
            id = Long.parseLong(offerId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Offer ID NaN");
        }
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Offer ID not found");
        }

        OrderItem orderItem = OrderItem.builder().
                order(order.get()).
                orderItemName(offer.get().getProduct().getProductName()).
                activationDate(new Date()).
                status("New").
                build();
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return ResponseEntity.ok(savedOrderItem);
    }

    @Override
    @Transactional
    public ResponseEntity removeItem(String itemId) {
        long id;
        try {
            id = Long.parseLong(itemId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        orderItemRepository.delete(orderItem.get());
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


}
