package com.comarch.fiberBilling.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDetailDTO {
    private long id;
    private ParameterDTO parameter;
    private String value;
    private BigDecimal priceRegular;
    private BigDecimal priceBusiness;
    private Date activeFrom;
    private Date activeTo;
}
