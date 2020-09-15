# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 013
* ID: requestheader
* Name: Request Header Annotation
* Relevant Spring features: @RestController, @GetMapping, @RequestHeader
* Description: This application shows a basic usage of the @RequestHeader annotation. 
* Taint flows: 
  * Open Redirect Attack
    * Description: The user can send a redirect url over a RequestHeader annotation through the field referer. The handler method redirects the request to the given link without any sanitization.  
    * Source: the value of the RequestHeader annotation in 
        * Line(s): 13
        * Class: MyController
        * Method: forward
    * Sink: the first parameter of the method call sendRedirect in
        * Line(s): 19
        * Class: MyController
        * Method: forward


