# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 011
* ID: requestattribute
* Name: Request Attribute Annotation
* Relevant Spring features: @RestController, @GetMapping, @ModelAttribute, @RequestAttribute
* Description: This application shows a basic usage of the @RequestAttribute annotation. 
* Taint flows: 
  * Information Leak
    * Description: The user can send a message to encrypt over a request parameter. The handler method requests attribute crypt algorithm to encrypt the message. Information leak exists.  
    * Source: value of the RequestAttribute annotations in 
        * Line(s): 22
        * Class: MyController
        * Method: encryptMessage
    * Sink: the first parameter of the method call append in
        * Line(s): 45
        * Class: MyController
        * Method: encryptMessage
    * Curl command:
        * curl http://localhost:8080/encryptMyMessage?message=hello
  * Information Leak
    * Description: The user can send a message to encrypt over a request parameter. The handler method requests attribute key size to encrypt the message. Information leak exists.  
    * Source: value of the RequestAttribute annotations in 
        * Line(s): 23
        * Class: MyController
        * Method: encryptMessage
    * Sink: the first parameter of the method call append in
        * Line(s): 45
        * Class: MyController
        * Method: encryptMessage
    * Curl command:
        * curl http://localhost:8080/encryptMyMessage?message=hello


