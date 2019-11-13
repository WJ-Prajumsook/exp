# @Condition Annotation

### Spring @Condition Annotation

- Introduced in Spring 4.0
- It is used to develop an “If-Then-Else” type of conditional checking for bean registration.
- @Condition is more higher level compared to @Profile annotation.
- [Official Spring documentation at] https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Conditional.html

### About this tutorial
The following guides illustrate how to use @Condition annotation in Spring boot application.
Example: You have a User interface and method to get the list of database. You have two implementations of the interface
one implementation connected to MySQL database and one connected to MongoDB.

Let's have a look at this in action.

### Run this project
```javascript
mvn spring-boot:run -DdbType=mysql/mongodb
```


