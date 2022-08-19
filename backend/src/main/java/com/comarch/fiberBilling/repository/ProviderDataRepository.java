package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ProviderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDataRepository extends JpaRepository<ProviderData, Long> {
}
