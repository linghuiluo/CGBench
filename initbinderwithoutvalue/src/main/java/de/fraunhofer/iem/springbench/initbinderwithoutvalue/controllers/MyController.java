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
    public void index(@RequestParam("user") String user, HttpServletResponse response) throws IOException {
        String responseHTML = createHTML(user);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //Method append is a sink, responseHTML will be displayed on client's browser. If there is a script then it will be
        //executed on client's browser.
        response.getWriter().append(responseHTML);
    }

    //This method creates a response HTML page.
    private static String createHTML(String name) {

        return "<html>\r\n" +
                "	<head>\r\n" +
                "		<meta charset=\"UTF-8\">\r\n" +
                "		<title>Hello World</title>\r\n" +
                "	</head>\r\n" +
                "	<body>\r\n" +
                "		<script>\r\n" +
                "			var c = \"secret=this is secret cookie;\" \r\n" +
                "			document.cookie = c;\r\n" +
                "		</script>\r\n" + "		<pre>\r\n" +
                "			Hello " + name + "!! Welcome to myWebsite\r\n." +
                "		</pre>\r\n" +
                "	</body>\r\n" +
                "</html>";
    }
}
