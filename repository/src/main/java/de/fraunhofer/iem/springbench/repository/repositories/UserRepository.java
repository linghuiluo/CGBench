package de.fraunhofer.iem.springbench.repository.repositories;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        return DriverManager.getConnection(url, "", "");
    }

    // More feature can be added like storeUser etc, but this is not required for this case study here.

    public String retrieveUser(String userName) {
        ResultSet res = null;
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            String query = "SELECT * FROM  User where userId='" + userName + "'";

            res = st.executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (res == null)
            return "No results found.";
        return res.toString();
    }
}
