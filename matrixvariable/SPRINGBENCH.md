# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 012
* ID: matrixvariable
* Name: Matrix Variable Annotation
* Relevant Spring features: @RestController, @GetMapping, @Configuration, @MatrixVariable
* Description: This application shows a basic usage of the @MatrixVariable annotation. 
* Taint flows: 
  * SQL injection
    * Description: The user can send a employee id over a MatrixVariable annotation. The handler method returns the data of that employee stored in a database. SQL injection vulnerability exists.  
    * Source: the return value of the method get in 
        * Line(s): 14
        * Class: MyController
        * Method: deleteFileByID
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 26
        * Class: MyController
        * Method: deleteFileByID


