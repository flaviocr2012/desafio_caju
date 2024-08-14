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

  * *Clean Code and Solid*:

    * **Single Responsibility Principle (SRP)**: Each class and method has a single responsibility, making the code easier to understand and maintain.
    * **Open/Closed Principle (OCP)**: The system is open for extensions but closed for modifications, allowing new features to be added without altering existing code.
    * **Dependency Inversion Principle** (DIP): The system uses dependency injection to manage objects and their interactions, promoting low coupling.

## **Technologies Used**

   *  **Java**: The programming language used for implementing the system.
   *  **Spring Boot**: A framework used to simplify the development and dependency management of the application.
   *  **Spring Data JPA**: A framework for abstracting and simplifying database access.
   *  **H2 Database**: An in-memory database used to facilitate development and testing.
   *  **JUnit and Mockito**: Tools for creating unit tests, ensuring the quality and reliability of the code.
   *  **Postman**: Used to test API endpoints by simulating HTTP requests.

## **Testing**

   * **Comprehensive Coverage**: The application has extensive test coverage, including unit tests to validate business rules and integration tests to ensure correct interaction between components.
   * **In-Memory Database**(H2): Tests are run on an H2 database, ensuring that data is persisted only in memory, facilitating the development cycle.

## **Testing Instructions**

   1. Clone the repository.
   2. Run the application via IDE or command line.
   3. Access Postman to make test requests to the configured endpoints.
   4. The H2 database can be accessed via http://localhost:8080/h2-console to view persisted data during execution.

**ps**: Before you start testing, remember to do a query to insert the customer`s data. For instance:
> INSERT INTO account (account_id, food_balance, meal_balance, cash_balance)
VALUES ('123', 200.00, 50.00, 300.00);

**ps**: You can find data base info in the application properties file. 


   
