# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Rest Controller

* Test application: 002
* ID: simplerestcontroller
* Name: Simple Rest Controller
* Relevant Spring features: @Controller, @RequestMapping
* Description: This application shows a basic usage of the @RestController annotation. A class annotated with @RestController can have handler methods which need to be considered as entry points for the call graph. The handler method in this application is annotated with @RequestMapping. 
* Taint flows: 
  * Command injection
    * Description: The user can send a message over a request parameter which is directly returned in the response body without proper sanitization. 
    * Source: the return value of the method getParameter in SimpleRestController within the method sendMessageBack (line 13).
    * Sink: the return statement of the method sendMessageBack (line 14).


