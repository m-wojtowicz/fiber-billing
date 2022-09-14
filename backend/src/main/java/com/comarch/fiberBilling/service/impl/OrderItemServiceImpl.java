package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderItemMapper;
import com.comarch.fiberBilling.model.api.response.GetAllOrderItems;
import com.comarch.fiberBilling.model.api.response.GetAllProductParameters;
import com.comarch.fiberBilling.model.api.response.GetAllProducts;
import com.comarch.fiberBilling.model.api.response.GetAllUserProducts;
import com.comarch.fiberBilling.model.entity.*;
import com.comarch.fiberBilling.repository.*;
import com.comarch.fiberBilling.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final ClientDataRepository clientDataRepository;
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;
    private final OrderItemMapper orderItemMapper = OrderItemMapper.INSTANCE;

    @Override
    public ResponseEntity getUserProducts(String userId, int pageNo, String filter, String filterType) {
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

        List<OrderItem> unfilteredItems = orderItemRepository.getAllUserProducts(id).orElse(null);
        if (unfilteredItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
        }

        List<OrderItem> filteredItems = new ArrayList<>();
        List<OrderItem> finalList = null;

        if (Objects.equals(filter, "")) {
            finalList = unfilteredItems;
        } else if (filterType.equalsIgnoreCase("id")) {
            unfilteredItems.forEach(item -> {
                if (String.valueOf(item.getId()).contains(filter)) filteredItems.add(item);
            });
        } else if (filterType.equalsIgnoreCase("status")) {
            unfilteredItems.forEach(item -> {
                if (item.getStatus().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) filteredItems.add(item);
            });
        } else if (filterType.equalsIgnoreCase("creation date")) {
            try {
                LocalDate date = LocalDate.parse(filter.replace("/", ""), DateTimeFormatter.BASIC_ISO_DATE);
                unfilteredItems.forEach(item -> {
                    LocalDate orderItemDate = LocalDate.ofInstant(item.getActivationDate().toInstant(), ZoneOffset.UTC);
                    if (orderItemDate.isEqual(date)) filteredItems.add(item);
                });
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (filterType.equalsIgnoreCase("name")) {
            unfilteredItems.forEach(item -> {
                if (item.getOrderItemName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) filteredItems.add(item);
            });
        } else {
            finalList = unfilteredItems;
        }

        if (finalList == null) {
            finalList = filteredItems;
        }

        if (finalList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        PagedListHolder<OrderItem> page = new PagedListHolder<OrderItem>(finalList);
        page.setPageSize(3);
        page.setPage(pageNo);

        List<GetAllProducts> allUserProducts = new ArrayList<>();
        page.getPageList().forEach(orderItem -> {
            BigDecimal cost = new BigDecimal("0");
            List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(orderItem);
            if (Objects.equals(clientData.get().getClientType().getType(), "regular")) {
                for (var orderItemParameter : orderItemParameters) {
                    if (!orderItemParameter.getParameterDetail().isMonthly())
                        cost = cost.add(orderItemParameter.getParameterDetail().getPriceRegular());
                }
            } else {
                for (var orderItemParameter : orderItemParameters) {
                    cost = cost.add(orderItemParameter.getParameterDetail().getPriceBusiness());
                }
            }
            allUserProducts.add(GetAllProducts.builder()
                    .id(orderItem.getId())
                    .orderItemName(orderItem.getOrderItemName())
                    .activationDate(orderItem.getActivationDate())
                    .status(orderItem.getStatus())
                    .cost(cost)
                    .build());
        });

        GetAllUserProducts response = new GetAllUserProducts();
        response.setProducts(allUserProducts);
        response.setTotal(Long.valueOf(page.getPageCount()));
        return ResponseEntity.ok(response);
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
                status("NEW").
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
