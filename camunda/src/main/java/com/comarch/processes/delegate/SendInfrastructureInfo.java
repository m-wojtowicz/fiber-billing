package com.comarch.processes.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendInfrastructureInfo implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("SendInfrastructureStatus")
                .processInstanceBusinessKey(delegateExecution.getProcessBusinessKey())
                .setVariables(delegateExecution.getVariables())
                .correlateWithResult();
    }
}
