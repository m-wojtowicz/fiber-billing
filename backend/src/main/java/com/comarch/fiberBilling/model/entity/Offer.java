package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`OFFER`")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_OFFER`", nullable = false)
    private long id;
    @Column(name = "`OFFER_NAME`", length = 45, nullable = false)
    private String offerName;
    @Column(name = "`ONE-TIME_CHARGE`", nullable = false)
    private BigDecimal oneTimeCharge;
    @JoinColumn(name = "`ID_PRODUCT`", nullable = false)
    @ManyToOne
    private Product product;
}
