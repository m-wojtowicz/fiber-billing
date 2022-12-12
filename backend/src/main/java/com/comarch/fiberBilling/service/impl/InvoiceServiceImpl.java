package com.comarch.fiberBilling.service.impl;

import com.comarch.fiberBilling.model.api.response.*;
import com.comarch.fiberBilling.model.entity.*;
import com.comarch.fiberBilling.repository.*;
import com.comarch.fiberBilling.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemParameterRepository orderItemParameterRepository;
    private final ClientDataRepository clientDataRepository;

    @Override
    public ResponseEntity getUserInvoices(String userId, int pageNo, int rowsCount) {
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
        List<Invoice> invoices = new ArrayList<>();
        orders.forEach(o -> {
            invoices.addAll(invoiceRepository.findByOrder(o));
        });

        PagedListHolder<Invoice> page = new PagedListHolder<>(invoices);
        MutableSortDefinition sortDefinition = new MutableSortDefinition("id", true, true);
        page.setSort(sortDefinition);
        page.resort();
        page.setPageSize(rowsCount);
        page.setPage(pageNo);

        List<GetAllInvoices> allUserInvoices = new ArrayList<>();
        page.getPageList().forEach(invoice -> {
            Order order = invoice.getOrder();
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
                        if (orderItemParameter.getParameterDetail().isMonthly())
                            monthlyCost = monthlyCost.add(orderItemParameter.getParameterDetail().getPriceRegular());
                        else
                            oneTimeCharge = oneTimeCharge.add(orderItemParameter.getParameterDetail().getPriceRegular());
                    } else {
                        if (orderItemParameter.getParameterDetail().isMonthly())
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

            GetAllOrders orderItem = GetAllOrders.builder()
                    .id(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .orderStartDate(order.getOrderStartDate())
                    .orderEndDate(order.getOrderEndDate())
                    .oneTimeCharge(totalOneTimeCharge)
                    .monthlyCharge(totalMonthlyCost)
                    .items(items)
                    .build();

            ProviderData providerData = invoice.getDataProvider();
            GetAllProviderData providerDataItem = GetAllProviderData.builder()
                    .id(providerData.getId())
                    .name(providerData.getProviderName())
                    .address(providerData.getAddress())
                    .bankAccount(providerData.getBankAccount())
                    .taxId(providerData.getTaxId())
                    .build();

            allUserInvoices.add(GetAllInvoices.builder()
                            .id(invoice.getId())
                            .invoiceDate(invoice.getInvoiceDate())
                            .dueDate(invoice.getDueDate())
                            .paymentDate(invoice.getPaymentDate())
                            .order(orderItem)
                            .providerData(providerDataItem)
                            .build());
        });

        return ResponseEntity.ok(GetAllUserInvoices.builder().invoices(allUserInvoices).total(Long.valueOf(page.getNrOfElements())).build());
    }
}
