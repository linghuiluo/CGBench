# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 022
* ID: modelattributewithaddattribute
* Name: Model Attribute Annotation with addAttribute
* Relevant Spring features: @RestController, @RequestMapping, @ModelAttribute (with addAttribute)
* Description: This application shows a basic usage of the @ModelAttribute annotation with addAttribute on method level. 
* Taint flows: 
  * XSS Attack
    * Description: The user can send a userName over a request parameter. This userName will be added in the model, later this model will be sent in the response body through the append method call, therefore XSS attack exist.  
    * Source: the return value of the method getParameter in 
        * Line(s): 23
        * Class: MyController
        * Method: addUserName
    * Sink: the first parameter of the method call append in
        * Line(s): 48
        * Class: MyController
        * Method: login
    * Curl command: 
        * curl http://localhost:8080/login?userName=%3Cscript%3E%20%20%20alert(%22Yourcookie%3D%22%20%2B%20document.cookie)%20%20%20%20%3C%2Fscript%3E
    * URL decoded link: (Only for human understanding)
		* http://localhost:8080/login?userName=<script> alert("Yourcookie=" + document.cookie) </script>
  * Trust Boundary Violation Attack
    * Description: The user can send a user id over a request parameter. This userName will be sent in the response through append method call, therefore Reflexive XSS attack exist.
    * Source: the return value of the method getParameter in 
        * Line(s): 39
        * Class: MyController
        * Method: login
    * Sink: the first parameter of the method call append in
        * Line(s): 48
        * Class: MyController
        * Method: login
    * Curl command: 
        * curl http://localhost:8080/login?userName=%3Cscript%3E%20%20%20alert(%22Yourcookie%3D%22%20%2B%20document.cookie)%20%20%20%20%3C%2Fscript%3E
    * URL decoded link: (Only for human understanding)
		* http://localhost:8080/login?userName=<script> alert("Yourcookie=" + document.cookie) </script>


