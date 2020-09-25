package de.fraunhofer.iem.springbench.repository.controllers;

import de.fraunhofer.iem.springbench.repository.repositories.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyController {
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        String user = request.getParameter("user");

        if (user == null)
            return "No user is provided.";

        AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext();
        myContext.scan("de.fraunhofer.iem.springbench.repository");
        myContext.refresh();

        UserRepository userRepository = myContext.getBean(UserRepository.class);

        return userRepository.retrieveUser(user);
    }
}