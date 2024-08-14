# **Credit Card Authorization Challenge** 

## **Overview**

This project is a Credit Card Authorization system implemented in Java using the Spring Boot framework. The system was designed following Clean Code and SOLID principles, ensuring clean, modular, and maintainable code. The system's architecture was carefully planned to separate responsibilities and facilitate scalability.

## **Application Architecture**

  * *Layer Archictecure*:

    * **controllers**: Contains the control logic where HTTP requests are handled and directed to the corresponding service.
    * **services**: Contains the business logic. The main service is responsible for authorizing transactions.
    * **helpers**: Contains utility classes that support the business logic, abstracting reusable functionalities.
    * **models**: Contains the system's entities, representing the data that is persisted in the database.
    * **repositories**: Contains interfaces that interact with the database using Spring Data JPA.
    * **exceptions**: Contains custom exception classes that facilitate centralized error handling.
    * **constants**: Contains constants used throughout the application, ensuring centralization and ease of maintenance.
    * **dtos**: Contains Data Transfer Objects, responsible for securely and efficiently transporting data between different application layers.
  


