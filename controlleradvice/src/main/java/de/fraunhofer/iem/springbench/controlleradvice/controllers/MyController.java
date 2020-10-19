package de.fraunhofer.iem.springbench.controlleradvice.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
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