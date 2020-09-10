package de.fraunhofer.iem.springbench.patchmapping.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class MyController {
    @PatchMapping(value = "/changeEmail", produces = MediaType.TEXT_PLAIN_VALUE)
    public String changeEmail(HttpServletRequest request, HttpServletResponse response) {

        String UID = request.getParameter("UID");
        String newEmail = request.getParameter("newEmail");

        if (UID == null && newEmail == null) {
            return "Please provide both user id and new email id";
        } else if (UID == null) {
            return "Please provide user id";
        } else if (newEmail == null) {
            return "Please provide new email id";
        }

        return updateEmail(UID, newEmail);
    }

    public String updateEmail(String UID, String newEmail) {
        int exitCode = -100;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql";
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();

            String query = "update users set email = " + newEmail + " where id = " + UID;

            exitCode = st.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (exitCode == 0)
            return "Update success";
        return "Update failed wth the exit code = " + exitCode;
    }
}
