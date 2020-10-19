package de.fraunhofer.iem.springbench.exceptionhandler.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
    @ExceptionHandler(value = {NumberFormatException.class})
    protected String invalidNumberExceptionHandler(HttpServletRequest request) {

        String uid = request.getParameter("uid");

        return "Invalid user id = " + uid;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void retrieveUserInformation(HttpServletRequest request) {
        String uid = request.getParameter("uid");

        int userID = Integer.parseInt(uid);  // throws NumberFormatException if user gives non-numbers

        // retrieves the user information from the database using userID
    }
}