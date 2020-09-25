# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 036
* ID: bean
* Name: Bean Annotation
* Relevant Spring features: @RestController, @GetMapping, @Configuration, @Bean
* Description: This application shows a basic usage of the @Bean annotation. 
* Taint flows: 
  * Information leak
    * Description: The user can send a message to encrypt over a request parameter. The handler method takes the crypt algorithm and the key size from the bean to encrypt the message. Information leak exists.  
    * Source: the return value of the method getBean in 
        * Line(s): 23
        * Class: MyController
        * Method: encryptMessage
    * Sink: the first parameter of the method call append in
        * Line(s): 45
        * Class: MyController
        * Method: encryptMessage
    * Curl command: 
        * curl http://localhost:8080/encryptMyMessage?message=hello


