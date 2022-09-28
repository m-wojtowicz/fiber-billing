package com.comarch.fiberBilling.service;

import org.springframework.http.ResponseEntity;

public interface InvoiceService {
    ResponseEntity getUserInvoices(String userId, int pageNo, int rowsCount);

}
