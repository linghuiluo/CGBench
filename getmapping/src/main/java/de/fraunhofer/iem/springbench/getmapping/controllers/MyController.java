package de.fraunhofer.iem.springbench.getmapping.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String index(HttpServletRequest request, HttpServletResponse response) {

		String user = request.getParameter("user");

		if (user == null)
			return "No user is provided.";
		return getValue(user);
	}

	public String getValue(String value) {

            ResultSet res=null;
                try {
                	Class.forName("com.mysql.jdbc.Driver");
                	String url = "jdbc:mysql://localhost:3306/mysql";
                    Connection conn = DriverManager.getConnection(url,"","");
	                Statement st = conn.createStatement();
	                String query = "SELECT * FROM  User where userId='" + value + "'";

	                res = st.executeQuery(query);
	                
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                if(res==null)
                	return "No results found.";
                return res.toString();
				

	}

}
