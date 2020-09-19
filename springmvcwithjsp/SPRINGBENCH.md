# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 028
* ID: springmvcwithjsp
* Name: Spring MVC with JSP
* Relevant Spring features: @Controller, @RequestMapping, @ModelAttribute (on all level)
* Description: Simple Spring application with single controller that makes use of Spring MVC with JSP view
* Taint flows: 
  * SQL injection
    * Description: The user can send a userName over a request parameter. The handler method returns the data of that user's order stored in a database. SQL injection vulnerability exists.  
    * Source: the return value of the method getParameter in 
        * Line(s): 19
        * Class: MyController
        * Method: newUser
    * Sink: the first parameter of the method call executeQuery in
        * Line(s): 55
        * Class: MyController
        * Method: printOrderedItemsInformation
    * Curl command: 
        * curl http://localhost:8080/?userName=attacker%27%20OR%20%271%27%20=%20%271
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/?userName=attacker' OR '1' = '1


