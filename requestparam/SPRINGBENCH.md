# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 009
* ID: requestparam
* Name: Request Param Annotation
* Relevant Spring features: @RestController, @GetMapping, @RequestParam
* Description: This application shows a basic usage of the @RequestParam annotation. 
* Taint flows: 
  * SQL injection
    * Description: This is similar to the test 004 with @RequestParam annotation. The user can send a user id over a RequestParam annotation. The handler method returns the data of that user stored in a database. SQL injection vulnerability exists.  
    * Source: the value of the RequestParam annotation in 
        * Line(s): 13
        * Class: MyController
        * Method: index
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 29
        * Class: MyController
        * Method: getValue
    * Curl command:
        * curl http://localhost:8080/?user=2%27%20OR%20%271%27%20%3D%20%271
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?user=2' OR '1' = '1


