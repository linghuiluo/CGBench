# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 006
* ID: putmapping
* Name: Put Mapping Annotation
* Relevant Spring features: @PutMapping, @RestController, @PathVariable
* Description: This application shows a basic usage of the @PutMapping annotation. 
* Taint flows: 
  * SQL injection
    * Description:   
    * Source: value of the PathVariable annotation in 
        * Line(s): 18
        * Class: MyController
        * Method: user
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 21
        * Class: UsersRepository
        * Method: exists
    * Curl command: 
        * curl -X PUT http://localhost:8080/user/5


