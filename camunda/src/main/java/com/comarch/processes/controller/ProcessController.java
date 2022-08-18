package com.comarch.processes.controller;

import com.comarch.processes.api.request.ProcessRequest;
import com.comarch.processes.dto.ProcessDTO;
import com.comarch.processes.service.CamundaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProcessController {

    private final CamundaService camundaService;

    @Operation(summary = "Start new process instance")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/camunda/process/new")
    public ProcessDTO createProcess() {
        return camundaService.create();
    }

    @Operation(summary = "Complete user task")
    @PostMapping(value = "/camunda/process/complete")
    public ProcessDTO completeTask(@RequestBody ProcessRequest processRequest) {
        return camundaService.completeTask(processRequest);
    }

}
