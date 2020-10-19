# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 027
* ID: controlleradvice
* Name: Controller Advice Annotation
* Relevant Spring features: @RestController, @GetMapping, @ExceptionHandler, @EnableWebMvc, @ControllerAdvice
* Description: This application shows a basic usage of the @ControllerAdvice annotation. 
* Taint flows: 
  * Reflexive XSS
    * Description: The user can send a user id over a request parameter. The handler method returns the user information, if invalid characters are provided then exception handler will send a string invalid user id with the given user id. Reflexive XSS vulnerability exists.  
    * Source: the return value of the getParameter method in 
        * Line(s): 13
        * Class: MyController
        * Method: retrieveUserInformation
    * Sink: the first parameter of the method call append in
        * Line(s): 20
        * Class: MyExceptionHandler
        * Method: invalidNumberExceptionHandler
    * Curl command: 
        * curl http://localhost:8080/?uid=%3Cscript%3E%20%20%20alert(%22Yourcookie%3D%22%20%2B%20document.cookie)%20%20%20%20%3C%2Fscript%3E
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?uid=<script>   alert("Yourcookie=" + document.cookie)    </script>


