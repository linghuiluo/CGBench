# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 015
* ID: cookievalue
* Name: Cookie Value Annotation
* Relevant Spring features: @RestController, @GetMapping, @CookieValue
* Description: This application shows a basic usage of the @CookieValue annotation. 
* Taint flows: 
  * SQL injection
    * Description: The handler method uses the cookie value userID and password to validate the session and performs some sensitive information. Since cookie value can be changed by the user, and the proper sanitization not used for cookie values before using it in SQL commands, SQL injection vulnerability exists.  
    * Source: value of the CookieValue annotation in 
        * Line(s): 13
        * Class: MyController
        * Method: someSensitiveOperation
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 32
        * Class: MyController
        * Method: validateUser
    * Curl command: 
        * curl --cookie "userID=victimuser%27%20UNION%20SELECT%20%27iamevil;password=iamevil" http://localhost:8080/someSensitiveOperation
    * URL decoded link: (Only for human understanding)
        * --cookie "userID=victimuser' UNION SELECT 'iamevil;password=iamevil" http://localhost:8080/someSensitiveOperation


