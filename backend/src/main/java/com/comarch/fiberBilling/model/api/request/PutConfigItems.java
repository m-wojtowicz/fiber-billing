package com.comarch.fiberBilling.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutConfigItems {
    Long id;
    List<PutConfigItems> items;
}
