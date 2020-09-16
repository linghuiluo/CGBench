package de.fraunhofer.iem.springbench.handlerinterceptoraftercompletion.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // remove all the temporary data used by user and logout accordingly.
        String user = request.getParameter("user");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append("User logged out");
    }
}
