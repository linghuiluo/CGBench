# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 004
* ID: getmapping
* Name: Get Mapping Annotation
* Relevant Spring features: @RestController, @GetMapping
* Description: This application shows a basic usage of the @GetMapping annotation. 
* Taint flows: 
  * SQL injection
    * Description: The user can send a user id over a request parameter. The handler method returns the data of that user stored in a database. SQL injection vulnerability exists.  
    * Source: the return value of the method getParameter in 
        * Line(s): 21
        * Class: MyController
        * Method: index
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 38
        * Class: MyController
        * Method: getValue


