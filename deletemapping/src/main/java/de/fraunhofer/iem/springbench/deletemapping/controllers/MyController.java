package de.fraunhofer.iem.springbench.deletemapping.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @DeleteMapping(value = "/delete", produces = MediaType.TEXT_PLAIN_VALUE)
    public String deleteFileByID(HttpServletRequest request, HttpServletResponse response) {

        String UID = request.getParameter("UID");
        String fileName = request.getParameter("fileName");

        if (UID == null && fileName == null) {
            return "Please provide both user id and file name";
        } else if (UID == null) {
            return "Please provide user id";
        } else if (fileName == null) {
            return "Please provide file name";
        }

        String cmd = "del " + UID + "/" + fileName;

        String responseMessage = "";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            int exitCode = process.exitValue();
            if (exitCode == 0) {
                responseMessage = "Successfully Deleted";
            } else {
                responseMessage = "Could not deleted. Exit code = " + exitCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseMessage = "Something went wrong.\n" + e.getMessage();
        }

        return responseMessage;
    }
}
