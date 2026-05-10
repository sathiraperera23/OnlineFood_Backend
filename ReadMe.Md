# Online Food Ordering System - Backend

## Overview

This project is a backend REST API for an Online Food Ordering System developed using Spring Boot, Spring Security, Spring Data JPA, and MySQL.

The system supports:

* User registration and login
* JWT-based authentication
* Food and category management
* Cart operations
* Order processing
* Payment handling
* Role-based authorization

This project was developed as part of the CMJD Comprehensive Master Java Developer coursework.

---

# Technologies Used

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* MySQL
* Maven
* JWT Authentication
* Lombok

---

# Features

## Authentication

* User Registration
* User Login
* JWT Token Generation
* Password Encryption using BCrypt

## User Roles

* ADMIN
* CUSTOMER

## Food Management

* Create Food Items
* Update Food Items
* Delete Food Items
* View Food Items

## Category Management

* Create Categories
* Update Categories
* Delete Categories
* View Categories

## Cart Management

* Add Items to Cart
* Remove Items from Cart
* Clear Cart
* View Cart

## Order Management

* Place Orders
* View Orders
* Update Order Status

## Payment Management

* Create Payment
* Complete Payment
* Fail Payment
* View Payment Details

---

# Project Structure

```plaintext
src/main/java/com/example/Food
│
├── controller
├── service
│   ├── impl
├── repository
├── entity
├── security
├── exception
└── config
```

---

# Database Configuration

Configure the database in:

```plaintext
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# JWT Authentication

After successful login, the system returns a JWT token.

Use the token in request headers:

```plaintext
Authorization: Bearer YOUR_TOKEN
```

---

# API Endpoints

## Authentication

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Register new user |
| POST   | /auth/login    | Login and get JWT |

---

## Categories

| Method | Endpoint         |
| ------ | ---------------- |
| POST   | /categories      |
| GET    | /categories      |
| GET    | /categories/{id} |
| PUT    | /categories/{id} |
| DELETE | /categories/{id} |

---

## Food Items

| Method | Endpoint   |
| ------ | ---------- |
| POST   | /food      |
| GET    | /food      |
| GET    | /food/{id} |
| PUT    | /food/{id} |
| DELETE | /food/{id} |

---

## Cart

| Method | Endpoint             |
| ------ | -------------------- |
| GET    | /cart/{userId}       |
| POST   | /cart/add            |
| DELETE | /cart/remove         |
| DELETE | /cart/clear/{userId} |

---

## Orders

| Method | Endpoint                 |
| ------ | ------------------------ |
| POST   | /orders/place            |
| GET    | /orders/user/{userId}    |
| GET    | /orders/{orderId}        |
| PUT    | /orders/{orderId}/status |

---

## Payments

| Method | Endpoint                       |
| ------ | ------------------------------ |
| POST   | /payments/create               |
| PUT    | /payments/{paymentId}/complete |
| PUT    | /payments/{paymentId}/fail     |
| GET    | /payments/order/{orderId}      |

---

# Entity Relationships

* User → One-to-One → Cart
* Cart → One-to-Many → CartItem
* CartItem → Many-to-One → FoodItem
* Category → One-to-Many → FoodItem
* User → One-to-Many → Order
* Order → One-to-Many → OrderItem
* OrderItem → Many-to-One → FoodItem
* Order → One-to-One → Payment

---

# Exception Handling

Global exception handling is implemented using:

* `@RestControllerAdvice`
* Custom exceptions
* Structured API error responses

---

# Logging

Application logging is implemented using SLF4J.

Examples:

* Order placement logs
* Payment status logs
* Error logs

---

# Running the Project

## Clone Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_LINK
```

## Open Project

Open the project using IntelliJ IDEA.

## Run Application

Run the main Spring Boot application class.

Default server:

```plaintext
http://localhost:8080
```

---

# Future Improvements

* Swagger/OpenAPI Documentation
* DTO Pattern
* Unit Testing
* Docker Support
* Payment Gateway Integration
* Email Notifications

---

# Author

Sathira Perera

---

# Submission

This project was developed for the CMJD Comprehensive Master Java Developer coursework assignment.
