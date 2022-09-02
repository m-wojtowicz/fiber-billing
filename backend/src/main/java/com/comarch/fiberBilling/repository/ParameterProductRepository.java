package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.ParameterProduct;
import com.comarch.fiberBilling.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterProductRepository extends JpaRepository<ParameterProduct, Long> {

    List<ParameterProduct> findByProduct(Product product);
}
