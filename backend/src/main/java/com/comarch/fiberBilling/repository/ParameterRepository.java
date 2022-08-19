package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, String> {
}
