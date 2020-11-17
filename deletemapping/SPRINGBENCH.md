# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 007
* ID: deletemapping
* Name: Delete Mapping Annotation
* Relevant Spring features: @RestController, @DeleteMapping
* Description: This application shows a basic usage of the @DeleteMapping annotation. 
* Taint flows: 
  * Command injection
    * Description: The user can send an user id, and the file name over request parameter through HttpServletRequest object. The handler method appends these values to form a remove command and tries to execute the remove command. 
    Command injection vulnerability exists, since user inputs are not sanitized.  
    * Source: return value of getParameter method in
        * Line(s): 16 and 17
        * Class: MyController
        * Method: deleteFileByID
    * Sink: the first parameter of the method call exec in
        * Line(s): 31
        * Class: MyController
        * Method: deleteFileByID
    * Curl command: 
        * curl -X DELETE "http://localhost:8080/delete?UID=../../sensitivePath&fileName=sensitiveDirectory/sensitiveFile"

