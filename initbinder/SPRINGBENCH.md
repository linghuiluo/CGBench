# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 031
* ID: initbinder
* Name: Init Binder Annotation
* Relevant Spring features: @RestController, @GetMapping, @RequestParam, @InitBinder
* Description: This application shows a basic usage of the @InitBinder annotation. 
* Taint flows: 
  * Reflexive XSS
    * Description: The user can send a user id over a request parameter. The handler method returns the html page with the hello message with user id appended. Reflexive XSS vulnerability exists.  
    * Source: the value of the RequestParam annotation in 
        * Line(s): 23
        * Class: MyController
        * Method: index
    * Sink: the first parameter of the method call append in
        * Line(s): 30
        * Class: MyController
        * Method: index
    * Curl command: 
        * curl http://localhost:8080/?user=%3Cscript%3E%20%20%20alert(%22Yourcookie%3D%22%20%2B%20document.cookie)%20%20%20%20%3C%2Fscript%3E
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?user=<script>   alert("Yourcookie=" + document.cookie)    </script>


