package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`PRICE`")
public class Price {
    @Id
    @Column(name = "`ID_PRICE`", length = 45, nullable = false)
    private String id;
    @Column(name = "`PRICE_HOME`", nullable = false)
    private BigDecimal [] priceHome;
    @Column(name = "`PRICE_BUSINESS`", nullable = false)
    private BigDecimal [] priceBusiness;
}
