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
    * Description: The user can send a message to encrypt over a request parameter. The handler method requests attribute crypt algorithm and the key size to encrypt the message. Information leak exists.  
    * Source: value of the RequestAttribute annotations in MyController within the method encryptMessage (line 20 and 21).
    * Sink: the first parameter of the method call append within the method encryptMessage (line 45).


