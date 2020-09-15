# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 014
* ID: requestpart
* Name: Request Part Annotation
* Relevant Spring features: @RestController, @PostMapping, @RequestPart
* Description: This application shows a basic usage of the @RequestPart annotation. 
* Taint flows: 
  * SQL injection
    * Description: This is similar to the test 004 with @RequestPart annotation. The user can send a user id over a RequestPart annotation. The handler method returns the data of that user stored in a database. SQL injection vulnerability exists.  
    * Source: the value of the RequestPart annotation in 
        * Line(s): 13
        * Class: MyController
        * Method: index
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 28
        * Class: MyController
        * Method: getValue
    * Curl command: 
        * curl -X POST -F user="1' OR '1'='1" http://localhost:8080/


