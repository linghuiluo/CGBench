package de.fraunhofer.iem.springbench.requestattribute.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyController {
    @ModelAttribute
    public void cryptAttribute(HttpServletRequest request) {
        request.setAttribute("crypto_algorithm", "rsa");
        request.setAttribute("key_size", "256");
    }

    @GetMapping(value = "/encryptMyMessage")
    public void encryptMessage(@RequestAttribute("crypto_algorithm") String algorithm,
                               @RequestAttribute("key_size") String key_size,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");

        String responseMessage = "";
        boolean encryptStatus = false;

        if (algorithm.equals("rsa")) {
            encryptStatus = encryptUsingRSA(message);
        } else if (algorithm.equals("aes")) {
            encryptStatus = encryptUsingAES(message);
        }

        if (encryptStatus) {
            responseMessage = "Message encrypted using " + algorithm + key_size;
        } else {
            responseMessage = "Failed to encrypt the message using " + algorithm + key_size;
        }

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(responseMessage);
    }

    private boolean encryptUsingRSA(String message) {
        /*
          Try to encrypt using RSA algorithm, if success return true else return false.
         */

        return false;
    }

    private boolean encryptUsingAES(String message) {
        /*
          Try to encrypt using RSAESA algorithm, if success return true else return false.
         */

        return false;
    }
}


