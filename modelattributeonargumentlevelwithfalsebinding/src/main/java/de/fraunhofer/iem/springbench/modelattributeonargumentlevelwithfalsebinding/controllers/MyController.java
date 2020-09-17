package de.fraunhofer.iem.springbench.modelattributeonargumentlevelwithfalsebinding.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {
    @ModelAttribute("DefaultSecurityInformation")
    public Map<String, String> myAppDefaultSecurityInformation() {
        Map<String, String> defaultSecuritySettings = new HashMap<>();

        defaultSecuritySettings.put("encryptionAlgorithm", "RSA");
        defaultSecuritySettings.put("encryptionKeySize", "256");
        defaultSecuritySettings.put("encoder", "base64");

        return defaultSecuritySettings;
    }

    @RequestMapping("/")
    public void index(@ModelAttribute(value = "DefaultSecurityInformation", binding = false) Map<String, String> defaultSetting,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append("Server is running with the default setting: ").append(defaultSetting.toString());
    }
}
