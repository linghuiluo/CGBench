package de.fraunhofer.iem.springbench.requestheader.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void forward(@RequestHeader("referer") String redirectLink,
                        HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", redirectLink);
            response.getWriter().append("Redirecting to: ").append(redirectLink);
            response.sendRedirect(redirectLink);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
