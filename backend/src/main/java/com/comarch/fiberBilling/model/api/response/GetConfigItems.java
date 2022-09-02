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
public class GetConfigItems {
    Long id;
    String orderStatus;
    Date orderStartDate;
    BigDecimal oneTimeCharge;
    BigDecimal monthlyCharge;
    List<Items> items;
}

