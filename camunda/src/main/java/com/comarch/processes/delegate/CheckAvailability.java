package com.comarch.processes.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CheckAvailability implements JavaDelegate {

    private final String url = "jdbc:postgresql://localhost:5432/fiber-billing";
    private final String user = "fiber-billing";
    private final String password = "fiber-billing";
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean fiberExist = true;
        boolean ontExist = false;
        Map<String, Object> variables = new HashMap<>();
        variables.put("fiberExist", fiberExist);
        variables.put("ontExist", ontExist);
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement())
        {
            String query = "update \"SYSTEM\"\n" +
                    "SET \"IsFiberExist\"=" + fiberExist + ", \"OntExist\"=" + ontExist + "\n" +
                    "where \"SYSTEM\".\"ID\" = (\n" +
                    "\tselect \"ORDER\".\"SYSTEM_ID\" from \"ORDER\"\n" +
                    "\twhere \"ORDER\".\"BUSINESS_KEY\"= '" + delegateExecution.getProcessInstance().getProcessBusinessKey() + "'" +
                    ");";
            statement.executeUpdate(query);
        }
        delegateExecution.getProcessInstance().setVariables(variables);
    }
}
