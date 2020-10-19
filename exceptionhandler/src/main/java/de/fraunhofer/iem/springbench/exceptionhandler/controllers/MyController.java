package de.fraunhofer.iem.springbench.exceptionhandler.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @ExceptionHandler(value = {NumberFormatException.class})
    public void invalidNumberExceptionHandler(NumberFormatException ex, HttpServletResponse response) throws IOException {

        String uid = ex.getMessage();

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(uid);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void retrieveUserInformation(HttpServletRequest request) {
        String uid = request.getParameter("uid");

        try {
            int userID = Integer.parseInt(uid);  // throws NumberFormatException if user gives non-numbers

            // retrieves the user information from the database using userID
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("invalid user id = " + uid);
        }
    }
}