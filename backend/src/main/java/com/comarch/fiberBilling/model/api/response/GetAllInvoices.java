package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoices {
    private Long id;
    private Date paymentDate;
    private Date invoiceDate;
    private Date dueDate;
    private GetAllOrders order;
    private GetAllProviderData providerData;
}
