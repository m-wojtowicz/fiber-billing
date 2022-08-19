package com.comarch.processes.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class OrderComplete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("OrderComplete")
                .processInstanceBusinessKey(delegateExecution.getProcessBusinessKey())
                .setVariables(delegateExecution.getVariables())
                .correlate();
    }
}
