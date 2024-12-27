# Backend for E-Commerce Application
This project is a backend implementation for an e-commerce application. It provides APIs for user management, product management, order handling, and authentication using Spring Boot and MongoDB.
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
- Spring Security (JWT Authentication)
- Lombok
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
5. Access the APIs at:
   Base URL: http://localhost:8080/api/

6. Run you mysql server in port number 3306.
   for windows
   ```bash
   net start mysql
---

### **API Endpoints**
Documentation for the apis:https://app.getpostman.com/join-team?invite_code=41726ea682ba67721e02a0e8148a5f68583abb20201bd5cdfabd05ac50c6c4b5&target_code=7e0fe76aae898cf6265bfe9ceaa385ad
this is a post man api documentation, in this you will see a collection named Backend, select that collection and you will be abel to see the complete documentation.

## Error Handling
All errors are returned with a standard structure:
```json
{
  "error": "Error message",
  "details": "Additional information"
}

   