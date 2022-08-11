package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.OrderItemParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemParameterRepository extends JpaRepository <OrderItemParameter, Long> {
}
