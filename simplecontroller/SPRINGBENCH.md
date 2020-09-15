# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 001
* ID: simplecontroller
* Name: Simple Controller
* Relevant Spring features: @Controller, @GetMapping, @ResponseBody
* Description: This application shows a basic usage of the @Controller annotation. A class annotated with @Controller can have handler methods which need to be considered as entry points for the call graph. The handler method in this application is annotated with @GetMapping. 
* Taint flows: 
  * Command injection
    * Description: The user can send a message over a request parameter which is directly returned to the response body without proper sanitization. 
    * Source: the return value of the method getParameter in 
        * Line(s): 16
        * Class: SimpleController
        * Method: sendMessageBack
    * Sink: the first parameter of the constructor of ResponseEntity in 
        * Line(s): 16
        * Class: SimpleController
        * Method: sendMessageBack
    * Curl command:
        * curl http://localhost:8080/?message=alert(document.cookie);


