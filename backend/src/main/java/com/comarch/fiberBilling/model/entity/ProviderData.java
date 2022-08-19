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
@Builder
@Table(name = "`PROVIDER_DATA`")
public class ProviderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_PROVIDER_DATA`", nullable = false)
    private long id;
    @Column(name = "`PROVIDER_NAME`", length = 45, nullable = false)
    private String providerName;
    @Column(name = "`ADDRESS`", length = 45, nullable = false)
    private String address;
    @Column(name = "`TAX_ID`", nullable = false)
    private BigDecimal taxId;
    @Column(name = "`BANK_ACCOUNT`", length = 45, nullable = false)
    private BigDecimal bankAccount;
}
