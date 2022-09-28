package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Invoice;
import com.comarch.fiberBilling.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(
            value = "SELECT * FROM \"INVOICE\"",
            nativeQuery = true
    )
    Page<Invoice> getAllUserInvoices(Long id, Pageable pageable);
    List<Invoice> findByOrder(Order order);
}
