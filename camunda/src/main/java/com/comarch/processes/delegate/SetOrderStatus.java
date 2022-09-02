package com.comarch.processes.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.*;

public class SetOrderStatus implements JavaDelegate {
    private final String url = "jdbc:postgresql://localhost:5432/fiber-billing";
    private final String user = "fiber-billing";
    private final String password = "fiber-billing";
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try(Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement())
        {
            String query = "UPDATE \"ORDER\" SET \"ORDER_STATUS\" = 'Active' WHERE \"ORDER\".\"ID_ORDER\" = " + delegateExecution.getVariables().get("id").toString() + ";";
            statement.executeUpdate(query);
        }
    }
}
