package de.fraunhofer.iem.springbench.handlerinterceptoradapterprehandle.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void login(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        // Authenticate the user and login accordingly.
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append("User logged in");
    }
}
