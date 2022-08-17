package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrders {
    private Long id;
    private String orderStatus;
    private Date orderStartDate;
    private Date orderEndDate;
    private BigDecimal oneTimeCharge;
    private BigDecimal monthlyCharge;
    private List<GetAllUserProducts> items;
}
