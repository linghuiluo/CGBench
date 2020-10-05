# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 038
* ID: sessionattribute
* Name: Session Attribute Annotation
* Relevant Spring features: @RestController, @GetMapping, @SessionAttribute
* Description: This application shows a basic usage of the @SessionAttribute annotation. 
* Taint flows: 
  * Stored XSS
      * Description: The user can send a user id over a request parameter. One handler method stores the userName in the session attribute and the other handler method uses this session attribute. Stored XSS vulnerability exists.  
      * Source: the return value of the getParameter method in 
          * Line(s): 17
          * Class: MyController
          * Method: index
      * Sink: the first parameter of the method call append in
          * Line(s): 33
          * Class: MyController
          * Method: sendMessage
      * Curl command: (curl command should be run in the below order to perform the attack)
          * curl --cookie-jar cookies.txt -s -L http://localhost:8080/?userName=%3Cscript%3Ealert%28+document.cookie+%29%3B%3C%2Fscript%3E
          * curl --cookie cookies.txt --cookie-jar cookies.txt -s -L http://localhost:8080/sendMessage?message=hello
      * URL decoded link: (Only for human understanding)
          * http://localhost:8080/?userName=<script>   alert("Yourcookie=" + document.cookie)    </script>
          * http://localhost:8080/sendMessage?message=hello
  * Reflexive XSS
      * Description: The user can send a message over a request parameter. The handler method sends this message in response body. Reflexive XSS vulnerability exists.  
      * Source: the return value of the getParameter method in 
          * Line(s): 25
          * Class: MyController
          * Method: sendMessage
      * Sink: the first parameter of the method call append in
          * Line(s): 33
          * Class: MyController
          * Method: sendMessage
      * Curl command: (curl command should be run in the below order to perform the attack)
          * curl --cookie-jar cookies.txt -s -L http://localhost:8080/?userName=attacker
          * curl --cookie cookies.txt --cookie-jar cookies.txt -s -L http://localhost:8080/sendMessage?message=%3Cscript%3Ealert%28+document.cookie+%29%3B%3C%2Fscript%3E
      * URL decoded link: (Only for human understanding)
          * http://localhost:8080/?userName=attacker
          * http://localhost:8080/sendMessage?message=<script>   alert("Yourcookie=" + document.cookie)    </script>


