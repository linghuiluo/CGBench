package de.fraunhofer.iem.springbench.requestpart.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class MyController {
    @PostMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index(@RequestPart("user") String user) {
        if (user == null)
            return "No user is provided.";
        return getValue(user);
    }

    public String getValue(String value) {
        ResultSet res = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            String query = "SELECT * FROM  User where userId='" + value + "'";

            res = st.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (res == null)
            return "No results found.";
        return res.toString();
    }
}
