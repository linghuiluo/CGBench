package de.fraunhofer.iem.springbench.sessionattribute.controllers;

import de.fraunhofer.iem.springbench.sessionattribute.HtmlPageCreationComponent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index(HttpServletRequest request) {
        request.getSession(true).setAttribute("userName", request.getParameter("userName"));
        return "Entered Online Chat";
    }

    @GetMapping(value = "/sendMessage", produces = MediaType.TEXT_PLAIN_VALUE)
    public void sendMessage(@SessionAttribute(value = "userName") String userName,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        HtmlPageCreationComponent htmlPageCreationComponent = new HtmlPageCreationComponent();

        String responseHTML = htmlPageCreationComponent.createHTML(userName, request.getParameter("message"));

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //Method append is a sink, responseHTML will be displayed on client's browser. If there is a script then it will be
        //executed on client's browser.
        response.getWriter().append(responseHTML);
    }
}
