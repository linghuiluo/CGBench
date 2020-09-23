package de.fraunhofer.iem.springbench.component.controllers;

import de.fraunhofer.iem.springbench.component.HtmlPageCreationComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("userName");

        AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext();
        myContext.scan("de.fraunhofer.iem.springbench.component");
        myContext.refresh();

        HtmlPageCreationComponent htmlPageCreationComponent = myContext.getBean(HtmlPageCreationComponent.class);

        String responseHTML = htmlPageCreationComponent.createHTML(user);

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //Method append is a sink, responseHTML will be displayed on client's browser. If there is a script then it will be
        //executed on client's browser.
        response.getWriter().append(responseHTML);
    }
}
