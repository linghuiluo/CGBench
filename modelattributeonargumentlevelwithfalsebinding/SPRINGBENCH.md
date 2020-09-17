# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 025
* ID: modelattributeonargumentlevelwithfalsebinding
* Name: Model Attribute Annotation on argument level with false binding
* Relevant Spring features: @RestController, @RequestMapping, @ModelAttribute (with return value), @ModelAttribute (on argument level)
* Description: This application shows a basic usage of the @ModelAttribute annotation on argument level with false binding.
* Taint flows: 
  * Information leak
    * Description: The handler method leaks the sensitive information stored in the Model.  
    * Source: the value of the ModelAttribute annotation on argument level in 
        * Line(s): 26
        * Class: MyController
        * Method: index
    * Sink: the first parameter of the method call append in
        * Line(s): 30
        * Class: MyController
        * Method: index
    * Curl command: 
        * curl http://localhost:8080/


