package de.fraunhofer.iem.springbench.pathvariable.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class MyController {
    @GetMapping(value = "/{user}/login")
    public void login(@PathVariable String user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession mySession = request.getSession();
        String pass = request.getParameter("pass");
        String resp = "";

        if (mySession.getAttribute("user") == null) {

            // Should authenticate the username and the password before set the session object
            mySession.setAttribute("user", user);

            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append(resp);
        } else {
            if (mySession.getAttribute("user").equals(user)) {
                resp = "Already logged in.";
            } else {
                resp = "Invalid username!! Please log in again";
            }

            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append(resp);
        }
    }
}

