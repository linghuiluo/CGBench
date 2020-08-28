# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 005
* ID: postmapping
* Name: Post Mapping Annotation
* Relevant Spring features: @Controller, @PostMapping, @Autowired, @Service
* Description: This application shows a basic usage of the @PostMapping annotation. It creates new post object for each POST request and created a folder with the content name. 
* Taint flows: 
  * Command Injection
    * Description: The user can send a a POST with a content through the request parameter. The handler created a post object and a folder. The value is not checked before the folder is created.  
    * Source: the return value of the method getParameter in MyController within the method createPost (line 27).
    * Sink: the first parameter of the method call exec in MyController within the method createPost (line 39).

