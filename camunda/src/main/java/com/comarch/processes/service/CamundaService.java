package com.comarch.processes.service;

import com.comarch.api.request.ProcessRequest;
import com.comarch.processes.dto.ProcessDTO;

public interface CamundaService {
    ProcessDTO create();

    ProcessDTO completeTask(ProcessRequest processRequest);
}
