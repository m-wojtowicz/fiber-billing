package com.comarch.processes.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class CheckAvailability implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = new HashMap<>();
        variables.put("fiberExist", true);
        variables.put("ontExist", false);
        delegateExecution.getProcessInstance().setVariables(variables);
    }
}
