package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderMapper;
import com.comarch.fiberBilling.model.api.response.GetAllOrders;
import com.comarch.fiberBilling.model.api.response.GetAllUserProducts;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import com.comarch.fiberBilling.repository.ClientDataRepository;
import com.comarch.fiberBilling.repository.OrderItemParameterRepository;
import com.comarch.fiberBilling.repository.OrderItemRepository;
import com.comarch.fiberBilling.repository.OrderRepository;
import com.comarch.fiberBilling.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ClientDataRepository clientDataRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;

    @Override
    public ResponseEntity getUserOrders(String userId) {
        long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<Order> orders = orderRepository.findByClientData(clientData.get());
        List<GetAllOrders> allOrders = new ArrayList<>();
        orders.forEach(order -> {
            String clientType = order.getClientData().getClientType().getType();
            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
            List<GetAllUserProducts> items = new ArrayList<>();
            BigDecimal monthlyCost = BigDecimal.ZERO;
            BigDecimal oneTimeCharge = BigDecimal.ZERO;
            BigDecimal totalMonthlyCost = BigDecimal.ZERO;
            BigDecimal totalOneTimeCharge = BigDecimal.ZERO;
            for( var orderItem: orderItems) {
                monthlyCost = BigDecimal.ZERO;
                oneTimeCharge = BigDecimal.ZERO;
                List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(orderItem);
                for (var orderItemParameter : orderItemParameters) {
                    if(clientType.equals("regular")) {
                        if (orderItemParameter.isMonthly())
                            monthlyCost = monthlyCost.add(orderItemParameter.getParameterDetail().getPriceRegular());
                        else
                            oneTimeCharge = oneTimeCharge.add(orderItemParameter.getParameterDetail().getPriceRegular());
                    } else {
                        if (orderItemParameter.isMonthly())
                            monthlyCost = monthlyCost.add(orderItemParameter.getParameterDetail().getPriceBusiness());
                        else
                            oneTimeCharge = oneTimeCharge.add(orderItemParameter.getParameterDetail().getPriceBusiness());
                    }

                }
                items.add(GetAllUserProducts.builder()
                        .id(orderItem.getId())
                        .orderItemName(orderItem.getOrderItemName())
                        .activationDate(orderItem.getActivationDate())
                        .status(orderItem.getStatus())
                        .cost(monthlyCost)
                        .oneTimeCharge(oneTimeCharge)
                        .build());
                totalMonthlyCost = totalMonthlyCost.add(monthlyCost);
                totalOneTimeCharge = totalOneTimeCharge.add(oneTimeCharge);
            }
            allOrders.add(GetAllOrders.builder()
                    .id(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .orderStartDate(order.getOrderStartDate())
                    .orderEndDate(order.getOrderEndDate())
                    .oneTimeCharge(totalOneTimeCharge)
                    .monthlyCharge(totalMonthlyCost)
                    .items(items)
                    .build());
        });
        return ResponseEntity.ok(allOrders);
    }

    @Override
    public ResponseEntity getOrder(String orderId) {
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

        OrderDTO orderDTO = orderMapper.orderToOrderDto(order.get());
        return ResponseEntity.ok(orderDTO);
    }

    @Override
    @Transactional
    public ResponseEntity addOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not provided");
        }
        Order order = orderMapper.orderDtoToOrder(orderDTO);
        Order newOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder.getId());
    }

    @Override
    @Transactional
    public ResponseEntity changeOrder(String orderId, OrderDTO orderDTO) {
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
        Order newOrder = orderMapper.orderDtoToOrder(orderDTO);
        newOrder.setId(order.get().getId());
        orderRepository.save(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToOrderDto(newOrder));
    }

    @Override
    @Transactional
    public ResponseEntity deleteOrder(String orderId) {
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
        orderRepository.delete(order.get());
        return ResponseEntity.status(HttpStatus.OK).body(orderMapper.orderToOrderDto(order.get()));
    }
}
