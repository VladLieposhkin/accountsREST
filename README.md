A simple demo example of a REST based application.

The application supports working with clients and their cryptocurrency accounts.
Every minute the application receives current cryptocurrency quotes from an external resource and updates the data.
With the same frequency, the application checks data on changes in the value of cryptocurrencies and, if there is a deviation of 5% or more, displays information in the log.

Stack used: 
Java 21, Spring Boot 3.2.6. Template - MVC. DBMS - PostgreSQL.

Data access - Spring Data JPA.

Data Validation - Validation using Hibernate validator.

Exception handling - ControllerAdvice.

Receiving data from an external resource - RestClient +  CompletableFuture.

Unit tests - JUnit5 + Testcontainers + H2.

Integration JUnit5 + Testcontainers + H2

The application runs at http://localhost:8081/swagger-ui/index.html
