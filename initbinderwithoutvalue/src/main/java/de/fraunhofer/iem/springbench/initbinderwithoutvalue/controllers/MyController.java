package de.fraunhofer.iem.springbench.initbinderwithoutvalue.controllers;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @InitBinder
    public void initBinder(HttpServletRequest request,
                           HttpServletResponse response,
                           WebDataBinder dataBinder) {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String user = request.getParameter("user");

        if (user.contains(" ")) {
            try {
                response.getWriter().append("Trimming username: " + user + ".........\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(" ", true);
            dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        }
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String user = request.getParameter("user");
        // Authenticate the user and continue with the process,
        // Here, we will consider that authentication fails and response with Invalid username

        response.getWriter().append("\n\nInvalid User Name.");
    }
}
