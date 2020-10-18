package de.fraunhofer.iem.springbench.putmapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepository {

	 public boolean exists(String id) {

		 ResultSet res = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/mysql";
	            Connection conn = DriverManager.getConnection(url, "", "");
	            Statement st = conn.createStatement();
	            String query = "SELECT password FROM  UserAuth where userId='" + id + "'";

	            res = st.executeQuery(query);

	            String validPass = res.getString("password");
	            if(validPass != null)
	            	return true;
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
