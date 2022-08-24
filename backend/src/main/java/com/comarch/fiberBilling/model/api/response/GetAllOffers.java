package com.comarch.fiberBilling.model.api.response;

import com.comarch.fiberBilling.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOffers {
    private Long id;
    private String offerName;
    private Product product;
}
