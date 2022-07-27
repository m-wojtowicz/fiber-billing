package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDataRepository extends JpaRepository<ClientData, Long> {
}
