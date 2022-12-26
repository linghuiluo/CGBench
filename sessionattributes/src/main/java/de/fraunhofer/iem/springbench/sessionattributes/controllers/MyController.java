package de.fraunhofer.iem.springbench.sessionattributes.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@SessionAttributes("userName")
public class MyController {
    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        String userName = request.getParameter("userName");

        if (userName != null)
            model.addAttribute("userName", userName);
        else
            model.addAttribute("userName", "Anonymous");

        return "Index";
    }

    @GetMapping(value = "/sendMessage")
    public String sendMessage(HttpServletRequest request, Model model) throws IOException {
        model.addAttribute("message", request.getParameter("message"));
        return "message";
    }
}
