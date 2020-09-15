# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 008
* ID: patchmapping
* Name: Patch Mapping Annotation
* Relevant Spring features: @RestController, @PatchMapping
* Description: This application shows a basic usage of the @PatchMapping annotation. 
* Taint flows: 
  * SQL injection
    * Description: The user can send an user id, and the new email id over request parameter through HttpServletRequest object. The handler method appends these values to form SQL update command to update the email and tries to execute it. SQL injection vulnerability exists, since user inputs are not sanitized.  
    * Source: return value of getParameter method in
        * Line(s): 19 and 20
        * Class: MyController
        * Method: changeEmail
    * Sink: the first parameter of the method call executeUpdate in
        * Line(s): 43
        * Class: MyController
        * Method: updateEmail
    * Curl command:
        * curl -X PATCH "http://localhost:8080/changeEmail?newEmail=attacker@evil.com&UID=1%20OR%201%20=%201"
    * URL decoded link: (Only for human understanding)
        * http://localhost:8080/changeEmail?newEmail=attacker@evil.com&UID=1 OR 1 = 1
