package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, String> {
}
