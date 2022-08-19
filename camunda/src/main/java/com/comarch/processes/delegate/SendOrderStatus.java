package com.comarch.processes.delegate;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendOrderStatus implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("OrderStatusInProgress")
                .processInstanceBusinessKey(delegateExecution.getProcessBusinessKey())
                .setVariables(delegateExecution.getVariables())
                .correlate();
    }
}
