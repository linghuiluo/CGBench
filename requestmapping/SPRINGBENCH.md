# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 003
* ID: requestmapping
* Name: Request Mapping Annotation
* Relevant Spring features: @RestController, @RequestMapping
* Description: This application shows a basic usage of the @RequestMapping annotation. 
* Taint flows: 
  * Open Redirect Attack
    * Description: The user can send a URL over a request parameter which is then redirected by the servlet. The URL value is not sanitized before used. 
    * Source: the return value of the method getParameter in 
        * Line(s): 18
        * Class: Controller
        * Method: forwardRequestUrl
    * Sink: the first parameter of the method call sendRedirect in
        * Line(s): 21
        * Class: Controller
        * Method: forwardRequestUrl
    * Curl command:
        * curl http://localhost:8080/home?url=http://iamevil.com/


