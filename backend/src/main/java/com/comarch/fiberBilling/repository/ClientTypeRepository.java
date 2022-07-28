package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {

    List<ClientType> findByType(String type);
}
