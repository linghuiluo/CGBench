package de.fraunhofer.iem.springbench.matrixvariable.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Map;

@RestController
public class MyController {
    @GetMapping("/retrieve/{retrieveParameters}")
    public String deleteFileByID(@MatrixVariable() Map<String, String> retrieveParameters) {
        String employeeID = retrieveParameters.get("EID");

        ResultSet res = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();

            String query = "SELECT * FROM  EMPLOYEE where EID=" + employeeID;

            res = st.executeQuery(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (res == null)
            return "No results found.";
        return res.toString();
    }
}
