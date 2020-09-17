# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 023
* ID: modelattributewithreturnvalue
* Name: Model Attribute Annotation with return value
* Relevant Spring features: @RestController, @RequestMapping, @ModelAttribute (with return value)
* Description: This application shows a basic usage of the @ModelAttribute annotation with return value on method level. 
* Taint flows: 
  * Trust Boundary Violation Attack
    * Description: The user can send a userName over a request parameter. The addUserName method adds the userName into the trusted data without authenticating, therefore Trust Boundary Violation attack exist.  
    * Source: the return value of the method getParameter in 
        * Line(s): 22
        * Class: MyController
        * Method: addUserName
    * Sink: the first parameter of the method call add in
        * Line(s): 27
        * Class: MyController
        * Method: addUserName
    * Curl command: 
        * curl http://localhost:8080/login?userName=IAmAttacker (performs the attack)
        * curl http://localhost:8080/currentUser (no attack is present in this command)


