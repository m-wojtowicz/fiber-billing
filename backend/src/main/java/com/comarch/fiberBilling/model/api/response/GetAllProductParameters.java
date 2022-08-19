package com.comarch.fiberBilling.model.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductParameters {
    private Long id;
    private String orderItemName;
    private String name;
    private String value;
    private BigDecimal priceRegular;
    private BigDecimal priceBusiness;
}
