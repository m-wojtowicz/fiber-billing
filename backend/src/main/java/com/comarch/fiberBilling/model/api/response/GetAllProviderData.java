package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProviderData {
    private Long id;
    private String name;
    private String address;
    private BigDecimal taxId;
    private BigDecimal bankAccount;
}
