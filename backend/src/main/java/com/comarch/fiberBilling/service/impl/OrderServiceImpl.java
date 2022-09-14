package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.mapper.OrderMapper;
import com.comarch.fiberBilling.model.api.request.PutConfigItems;
import com.comarch.fiberBilling.model.api.response.*;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.*;
import com.comarch.fiberBilling.repository.*;
import com.comarch.fiberBilling.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
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
public class OrderServiceImpl implements OrderService {

    private final ClientDataRepository clientDataRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;
    private final ParameterProductRepository parameterProductRepository;
    private final ProductRepository productRepository;

    private final ParameterDetailRepository parameterDetailRepository;

    @Override
    public ResponseEntity getUserOrders(String userId, int pageNo, String filter, String filterType) {
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

        List<Order> unfilteredOrders = orderRepository.findByClientData(clientData.get()).orElse(null);
        if (unfilteredOrders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No content");
        }

        List<Order> filteredOrders = new ArrayList<>();
        List<Order> finalList = null;

        if (Objects.equals(filter, "")) {
            finalList = unfilteredOrders;
        } else if (filterType.equalsIgnoreCase("id")) {
            unfilteredOrders.forEach(order -> {
                if (String.valueOf(order.getId()).contains(filter)) filteredOrders.add(order);
            });
        } else if (filterType.equalsIgnoreCase("status")) {
            unfilteredOrders.forEach(order -> {
                if (order.getOrderStatus().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) filteredOrders.add(order);
            });
        } else if (filterType.equalsIgnoreCase("creation date")) {
            try {
                LocalDate date = LocalDate.parse(filter.replace("/", ""), DateTimeFormatter.BASIC_ISO_DATE);
                unfilteredOrders.forEach(order -> {
                    LocalDate orderStartDate = LocalDate.ofInstant(order.getOrderStartDate().toInstant(), ZoneOffset.UTC);
                    LocalDate orderEndDate = LocalDate.ofInstant(order.getOrderEndDate().toInstant(), ZoneOffset.UTC);
                    if (orderStartDate.isEqual(date) || orderEndDate.isEqual(date)) filteredOrders.add(order);
                });
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            finalList = unfilteredOrders;
        }

        if (finalList == null) {
            finalList = filteredOrders;
        }

        if (finalList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        PagedListHolder<Order> page = new PagedListHolder<Order>(finalList);
        page.setPageSize(3);
        page.setPage(pageNo);


        List<GetAllOrders> allUserOrders = new ArrayList<>();
        page.getPageList().forEach(order -> {
            String clientType = order.getClientData().getClientType().getType();
            List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
            List<GetAllProducts> items = new ArrayList<>();
            BigDecimal monthlyCost;
            BigDecimal oneTimeCharge;
            BigDecimal totalMonthlyCost = BigDecimal.ZERO;
            BigDecimal totalOneTimeCharge = BigDecimal.ZERO;
            for (var orderItem : orderItems) {
                monthlyCost = BigDecimal.ZERO;
                oneTimeCharge = BigDecimal.ZERO;
                List<OrderItemParameter> orderItemParameters = orderItemParameterRepository.findByOrderItem(orderItem);
                for (var orderItemParameter : orderItemParameters) {
                    if (clientType.equals("regular")) {
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
                items.add(GetAllProducts.builder()
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
            allUserOrders.add(GetAllOrders.builder()
                    .id(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .orderStartDate(order.getOrderStartDate())
                    .orderEndDate(order.getOrderEndDate())
                    .oneTimeCharge(totalOneTimeCharge)
                    .monthlyCharge(totalMonthlyCost)
                    .items(items)
                    .build());
        });

        GetAllUserOrders response = new GetAllUserOrders();
        response.setOrders(allUserOrders);
        response.setTotal(Long.valueOf(page.getPageCount()));
        return ResponseEntity.ok(response);
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

    @Override
    @Transactional
    public ResponseEntity createOrder(String userId, String businessKey) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Optional<ClientData> clientData = clientDataRepository.findById(id);
        if (clientData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        Order newOrder = Order.builder().
                clientData(clientData.get()).
                orderStatus("NEW").
                orderStartDate(new Date()).
                orderEndDate(new Date()).
                businessKey(businessKey).
                build();

        orderRepository.flush();
        Order order = orderRepository.save(newOrder);
        return ResponseEntity.ok(order.getId());
    }

    @Override
    public ResponseEntity changeStatus(String orderId, String status) {
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
        Order newOrder = order.get();
        newOrder.setOrderStatus(status);
        orderRepository.save(newOrder);
        return ResponseEntity.ok(newOrder);
    }

    @Override
    public ResponseEntity getOpenOrder(String userId) {
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

        List<Order> orders = orderRepository.findByClientData(clientData.get()).orElse(null);

        for (Order order : orders) {
            if (order.getOrderStatus().toLowerCase(Locale.ROOT).equals("new")) {
                return ResponseEntity.ok(order.getId());
            }
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No open order found");
    }

    @Override
    public ResponseEntity getConfigItems(String orderId, String clientType) {
        long id;
        try {
            id = Long.parseLong(orderId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Order order = orderRepository.findById(id).orElse(null);
        if (order.equals(null)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }

        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);
        List<Items> items = new ArrayList<>();

        for (var orderItem : orderItemList) {
            Product product = productRepository.findByProductName(orderItem.getOrderItemName());

            List<ParameterProduct> parameterProductList = parameterProductRepository.findByProduct(product);
            List<Parameters> parametersList = new ArrayList<>();

            for (var parameterProduct : parameterProductList) {

                List<String> values = new ArrayList<>();
                List<BigDecimal> prices = new ArrayList<>();

                if (Objects.equals(clientType, "regular")) {
                    for (var parameterDetail : parameterProduct.getParameter().getParameterDetail()) {
                        values.add(parameterDetail.getValue());
                        prices.add(parameterDetail.getPriceRegular());
                    }
                } else {
                    for (var parameterDetail : parameterProduct.getParameter().getParameterDetail()) {
                        values.add(parameterDetail.getValue());
                        prices.add(parameterDetail.getPriceBusiness());
                    }
                }
                Parameters parameters = Parameters.builder()
                        .id(parameterProduct.getParameter().getId())
                        .name(parameterProduct.getParameter().getName())
                        .values(values)
                        .prices(prices)
                        .build();

                parametersList.add(parameters);
            }

            Items item = Items.builder()
                    .id(orderItem.getId())
                    .orderItemName(product.getProductName())
                    .status(orderItem.getStatus())
                    .oneTimeCharge(BigDecimal.ZERO)
                    .monthlyCharge(BigDecimal.ZERO)
                    .parameters(parametersList)
                    .build();
            items.add(item);
        }

        GetConfigItems getConfigItems = GetConfigItems.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderStartDate(order.getOrderStartDate())
                .oneTimeCharge(BigDecimal.ZERO)
                .monthlyCharge(BigDecimal.ZERO)
                .items(items)
                .build();

        return ResponseEntity.ok(getConfigItems);
    }

    @Override
    public ResponseEntity putConfigItems(String orderId, PutConfigItems data) {
        long id;
        try {
            id = Long.parseLong(orderId);
        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        }
        Order order = orderRepository.findById(id).orElse(null);
        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);

        for (var orderItem : orderItemList) {
            for(var d : data.getItems()) {
                if (orderItem.getId() == d.getId()) {
                    for(var v : d.getValues()){
                        ParameterDetail parameterDetail = parameterDetailRepository.findByValue(v);
                        OrderItemParameter orderItemParameter = OrderItemParameter.builder()
                                .orderItem(orderItem)
                                .parameterDetail(parameterDetail)
                                .monthly(true)
                                .payed(false)
                                .build();
                        orderItemParameterRepository.save(orderItemParameter);
                    }
                }
            }
        }
        return null;
    }
}
