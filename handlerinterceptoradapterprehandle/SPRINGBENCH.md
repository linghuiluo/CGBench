# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 019
* ID: handlerinterceptoradapterprehandle
* Name: Handler Interceptor Adapter preHandle method
* Relevant Spring features: @RestController, @GetMapping, @Configuration
* Description: This application shows a basic usage of the Handler Interceptor Adapter preHandle method.
* Taint flows: 
  * Log injection
    * Description: The user can send a user id over a request parameter. The preHandle method logs stating that the user is trying to login with the user id passed by the user. Log injection exist.  
    * Source: the return value of the method getParameter in 
        * Line(s): 12
        * Class: MyInterceptor
        * Method: preHandle
    * Sink: the first parameter of the method call info in
        * Line(s): 9
        * Class: MyLogger
        * Method: writeLog
    * Curl command: 
        * curl http://localhost:8080/?user=attacker+logged+in+successfully%0D%0Aabc+
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?user=attacker logged in successfully\nabc


