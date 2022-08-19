package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDataRepository extends JpaRepository<ClientData, Long> {
    Optional<ClientData> findByLogin(String login);
}
