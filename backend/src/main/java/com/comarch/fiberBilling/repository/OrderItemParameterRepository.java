package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemParameterRepository extends JpaRepository<OrderItemParameter, Long> {
    List<OrderItemParameter> findByOrderItem(OrderItem orderItem);
}
