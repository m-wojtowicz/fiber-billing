package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ClientData;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByClientData(ClientData clientData);
    Page<Order> findByClientDataAndId(ClientData clientData, Long id, Pageable pageable);
    Page<Order> findByClientDataAndOrderStatusContainingIgnoreCase(ClientData clientData, String status, Pageable pageable);
    Page<Order> findByClientDataAndOrderStartDateBetween(ClientData clientData, Date startDate, Date endDate, Pageable pageable);
    //Page<Order> findByClientDataAnd
    //List<Order> findByClientDataAndFilter(ClientData clientData, Pageable paging, Specification filter);
}
