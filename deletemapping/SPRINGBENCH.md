# Spring Bench

Spring Bench is a collection of simple Java Spring web applications with intentionally added taint flows. 
Each application tests a limited number of Spring features that have dynamic behavior when the application is executed. 
These dynamic features are challenging for static analysis writers especially for the task of constructing a sound and precise call graph.   


# Simple Controller

* Test application: 007
* ID: deletemapping
* Name: Delete Mapping Annotation
* Relevant Spring features: @RestController, @DeleteMapping, @RequestParam
* Description: This application shows a basic usage of the @DeleteMapping annotation. 
* Taint flows: 
  * Command injection
    * Description: The user can send a user id, and the file name over @RequestParam annotations. The handler method appends these values to form a remove command and tries to execute the remove command. 
    Command injection vulnerability exists, since user inputs are not sanitized.  
    * Source: both arguments of the method deleteFileByID handler annotated with @RequestParam. (line 13).
    * Sink: the first parameter of the method call exec within the handler method deleteFileByID (line 19).

