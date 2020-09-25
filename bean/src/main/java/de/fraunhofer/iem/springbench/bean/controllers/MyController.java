package de.fraunhofer.iem.springbench.bean.controllers;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class MyController {
    @GetMapping(value = "/encryptMyMessage")
    public void encryptMessage(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");

        AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext();
        myContext.scan("de.fraunhofer.iem.springbench.bean");
        myContext.refresh();

        HashMap<String, String> defaultSettings = (HashMap<String, String>) myContext.getBean("defaultEncryptSettings");

        String algorithm = defaultSettings.get("crypto_algorithm");
        String key_size = defaultSettings.get("key_size");

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
