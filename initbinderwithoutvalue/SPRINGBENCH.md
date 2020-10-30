# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 032
* ID: initbinderwithoutvalue
* Name: Init Binder Annotation without value
* Relevant Spring features: @RestController, @GetMapping, @RequestParam, @InitBinder (without value)
* Description: This application shows a basic usage of the @InitBinder annotation without value. 
* Taint flows: 
  * Reflexive XSS
    * Description: The user can send a user id over a request parameter. The inithandler method trims all the spaces and sends a respone with Trimming username with the appended user id. Reflexive XSS vulnerability exists.  
    * Source: the return value of the getParameter method in 
        * Line(s): 25
        * Class: MyController
        * Method: initBinder
    * Sink: the first parameter of the method call append in
        * Line(s): 29
        * Class: MyController
        * Method: initBinder
    * Curl command: 
        * curl http://localhost:8080/?user=%3Cscript%3E%20%20%20alert(%22Yourcookie%3D%22%20%2B%20document.cookie)%20%20%20%20%3C%2Fscript%3E
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?user=<script>   alert("Yourcookie=" + document.cookie)    </script>


