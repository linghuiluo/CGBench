package de.fraunhofer.iem.springbench.cookievalue.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class MyController {
    @GetMapping(value = "/someSensitiveOperation", produces = MediaType.TEXT_PLAIN_VALUE)
    public String someSensitiveOperation(@CookieValue(value = "userID") String userID,
                                         @CookieValue(value = "password") String password) {
        if (validateUser(userID, password)) {
            //Perform the sensitive operation
            return "Sensitive information";
        } else {
            return "Invalid User";
        }
    }

    public boolean validateUser(String userID, String password) {
        ResultSet res = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            String query = "SELECT password FROM  UserAuth where userId='" + userID + "'";

            res = st.executeQuery(query);

            String validPass = res.getString("password");

            return validPass.equals(password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
