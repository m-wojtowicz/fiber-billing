package com.comarch.fiberBilling.model.api.response;

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

public class GetAllProducts {
    private Long id;
    private String orderItemName;
    private Date activationDate;
    private String status;
    private BigDecimal cost;
    private BigDecimal oneTimeCharge;
}
