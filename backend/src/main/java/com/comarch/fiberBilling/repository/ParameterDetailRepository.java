package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ParameterDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterDetailRepository extends JpaRepository<ParameterDetail, Long> {
}
