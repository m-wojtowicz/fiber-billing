package com.comarch.fiberBilling.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderItems {
    private Long id;
    private String name;
    private Date activationDate;
    private String status;
}
