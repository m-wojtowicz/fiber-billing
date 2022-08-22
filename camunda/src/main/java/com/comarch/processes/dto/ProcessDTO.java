package com.comarch.processes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDTO {
    private String instanceID;
    private String definitionId;
    private String businessKey;
    private Boolean ended;
}
