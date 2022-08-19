package com.comarch.processes.service;

import com.comarch.processes.api.request.ProcessRequest;
import com.comarch.processes.dto.ProcessDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CamundaServiceImpl implements CamundaService {

    private final RuntimeService runtimeService;
    private final ProcessEngine processEngine;

    @Override
    public ProcessDTO create() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("fiberExist", null);
        variables.put("ontExist", null);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ClientProcess", "fiber-billing" + LocalDateTime.now(), variables);
        return ProcessDTO.builder()
                .definitionId(processInstance.getProcessDefinitionId())
                .instanceID(processInstance.getProcessInstanceId())
                .businessKey(processInstance.getBusinessKey())
                .ended(processInstance.isEnded())
                .build();
    }

    @Override
    public ProcessDTO completeTask(ProcessRequest processRequest) {
        Task task = processEngine.getTaskService()
                .createTaskQuery()
                .processInstanceBusinessKey(processRequest.getBusinessKey())
                .singleResult();
        processEngine.getTaskService().complete(task.getId());

        return createDTO(processRequest.getBusinessKey(), runtimeService, processRequest);
    }


    private static ProcessDTO createDTO(String businessKey, RuntimeService runtimeService, ProcessRequest processRequest) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(processRequest.getBusinessKey())
                .singleResult();
        return ProcessDTO.builder()
                .definitionId(processInstance.getProcessDefinitionId())
                .instanceID(processInstance.getProcessInstanceId())
                .businessKey(processInstance.getBusinessKey())
                .ended(processInstance.isEnded())
                .build();
    }
}
