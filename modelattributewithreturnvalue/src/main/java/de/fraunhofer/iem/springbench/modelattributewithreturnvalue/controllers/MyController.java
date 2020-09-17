package de.fraunhofer.iem.springbench.modelattributewithreturnvalue.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    // Assumed to be trusted data, that contains only trusted user
    private final List<String> currentUserInRoom = new ArrayList<>();

    @ModelAttribute("CurrentUsersInChatRoom")
    public List<String> addUserName(HttpServletRequest request) {
        String userName = request.getParameter("userName");

        if (userName != null) {
            if (!currentUserInRoom.contains(userName)) {
                //Trust boundary violation exist, since username is added without verifying username is trusted or untrusted.
                currentUserInRoom.add(userName);
            }
        }

        return currentUserInRoom;
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request,
                      HttpServletResponse response,
                      Model model) throws IOException {
        String userName = request.getParameter("userName");

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (userName == null) {
            response.getWriter().append("Please provide user name");
        } else {
            //Authenticate and then continue
            response.getWriter().append("Welcome!!! ").append(userName).append(model.toString());
        }
    }

    @RequestMapping("/currentUser")
    public String displayCurrentUserInRoom(Model model) {
        return model.toString();
    }
}
