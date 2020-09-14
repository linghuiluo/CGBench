# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 010
* ID: pathvariable
* Name: Path Variable Annotation
* Relevant Spring features: @RestController, @GetMapping, @PathVariable
* Description: This application shows a basic usage of the @PathVariable annotation. 
* Taint flows: 
  * Trust Boundary Violation
    * Description: The user can send a user name over PathVariable annotation. The handler method uses this value to set the session object  without authenticating the username and its password, therefore there exist the trust boundary violation.
    * Source: value of the PathVariable annotation in 
        * Line(s): 16
        * Class: MyController
        * Method: login
    * Sink: the second parameter of the method call setAttribute in
        * Line(s): 24
        * Class: MyController
        * Method: login

