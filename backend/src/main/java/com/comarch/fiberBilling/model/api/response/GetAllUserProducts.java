package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserProducts {
    private Long id;
    private String orderItemName;
    private Date activationDate;
    private String status;
    private Double cost;
}
