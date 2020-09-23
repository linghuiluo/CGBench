package de.fraunhofer.iem.springbench.service;

import org.springframework.stereotype.Service;

@Service
public class HtmlPageCreationService {
    //This method creates a response HTML page.
    public String createHTML(String userName) {
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
                "			Hello " + userName + "!! Welcome to myWebsite\r\n." +
                "		</pre>\r\n" +
                "	</body>\r\n" +
                "</html>";
    }
}
