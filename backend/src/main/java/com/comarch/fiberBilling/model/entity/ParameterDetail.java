package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "`PARAMETER_DETAIL`")
public class ParameterDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_PARAMETER_DETAIL`", length = 45, nullable = false)
    private long id;
    @JoinColumn(name = "`ID_PARAMETER`", nullable = false)
    @ManyToOne
    private Parameter parameter;
    @Column(name = "`VALUE`", length = 45, nullable = false)
    private String value;
    @Column(name = "`PRICE_REGULAR`", nullable = false)
    private BigDecimal priceRegular;
    @Column(name = "`PRICE_BUSINESS`", nullable = false)
    private BigDecimal priceBusiness;
    @Column(name = "`ACTIVE_FROM`", nullable = false)
    private Date activeFrom;
    @Column(name = "`ACTIVE_TO`", nullable = false)
    private Date activeTo;
}
