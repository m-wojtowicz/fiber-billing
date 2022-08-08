package com.comarch.fiberBilling.model.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`PARAMERTER_DETAIL`")
public class ParameterDetail {
    @Id
    @Column(name = "`ID_PARAMETER_DETAIL`", length = 45, nullable = false)
    private String id;
    @JoinColumn(name = "`ID_PARAMETER`", nullable = false)
    @ManyToOne
    private Parameter parameter;
    @Column(name = "`NAME`", length = 45, nullable = false)
    private String name;
    @Column(name = "`PRICE_REGULAR`", nullable = false)
    private BigDecimal priceRegular;
    @Column(name = "`PRICE_BUSINESS`", nullable = false)
    private BigDecimal priceBusiness;
    @Column(name = "`ACTIVE_FROM`", nullable = false)
    private Date activeFrom;
    @Column(name = "`ACTIVE_TO`", nullable = false)
    private Date activeTo;
}
