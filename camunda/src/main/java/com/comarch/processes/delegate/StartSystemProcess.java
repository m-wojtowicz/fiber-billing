package com.comarch.processes.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartSystemProcess implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = delegateExecution.getProcessInstance().getVariables();
        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("SystemNothingStart")
                .processInstanceBusinessKey(delegateExecution.getProcessBusinessKey())
                .setVariables(variables)
                .correlate();
    }
}
