# Backend for Inventory Management Application
This project is a backend implementation for an Inventory application. It provides APIs for user management, product management, order handling, and authentication using Spring Boot and MySQL.As of now in this we have only one role which is CUSTOMER. can be scalable to add more roles easily.
## Features
- User registration and authentication (JWT and cookies).
- CRUD operations for products.
- Order creation and retrieval.
- Soft delete for products.
- Pagination and filtering support for APIs.
- Secure API endpoints using Spring Security.
## Technologies Used
- Java 21
- Spring Boot
- MySql
- JPA ORM
- Spring Security (JWT Authentication)
- Lombok(Setters, Getters, Constructors)
- Maven
## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/SathwikSuggala/Backend
2. Navigate to the project directory
    ```bash
    cd Backend
3. Build the project
    ```bash
   mvn clean install
4. Run the application
    ```bash
   mvn spring-boot:run
5. if the commands are not working then please clone the repository and run using any java compiler. And you should have JDK 21
6. Access the APIs at:
   Base URL: http://localhost:8080/api/

7. Run your mysql server in port number 3306. and change the username and password of you database in application.properties file.  
   for windows
   ```bash
   net start mysql
---

### **API Endpoints**
Documentation for the apis:https://www.postman.com/spring-boot-6046/backend/documentation/q3wtbu2/backend
this is a post man api documentation, in this you will see a collection named Backend, select that collection and you will be abel to see the complete documentation.

## Error Handling
All errors are returned with a standard structure:
```json
{
  "error": "Error message",
  "details": "Additional information"
}

   