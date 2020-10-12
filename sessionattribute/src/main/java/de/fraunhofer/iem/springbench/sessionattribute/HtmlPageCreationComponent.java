package de.fraunhofer.iem.springbench.sessionattribute;

public class HtmlPageCreationComponent {
    //This method creates a response HTML page.
    public String createHTML(String userName, String message) {
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
                "			      " + userName + ": " + message +
                "		</pre>\r\n" +
                "	</body>\r\n" +
                "</html>";
    }
}
