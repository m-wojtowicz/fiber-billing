package com.comarch.processes.service;

import com.comarch.processes.api.request.ProcessRequest;
import com.comarch.processes.dto.ProcessDTO;

public interface CamundaService {
    ProcessDTO create(long orderId);

    ProcessDTO completeTask(ProcessRequest processRequest);
}
