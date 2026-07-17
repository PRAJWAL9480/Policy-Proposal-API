
# Policy Proposal Processing API

## Project Overview

This project is a Spring Boot REST API developed as part of the Spring Boot Backend Evaluation Assignment.

The application simulates a simplified insurance policy proposal system where customers can be created, policy proposals can be submitted, and audit records are maintained.

All data is stored in-memory using Java Collections (ConcurrentHashMap). No database is used.

---

## Technology Stack

- Java 25
- Spring Boot 3.5.16
- Maven
- Spring Validation
- REST API
- Java Collections (ConcurrentHashMap)

---

## Project Structure

src
├── controller
├── service
├── repository
├── model
├── dto
├── exception
└── DemoApplication.java

---

## Features

### Customer Module

- Create Customer
- Get All Customers
- Get Customer by ID
- Update Customer

### Proposal Module

- Create Proposal
- Get Proposal
- Submit Proposal
- Auto Generate Policy Number

### Reference Master

- Policy Terms
- Payment Frequency

### Audit

- Stores proposal submission history

---

## Business Rules

- Customer age must be between 18 and 65 years.
- Policy term must be one of:
  - 10
  - 15
  - 20
  - 25
  - 30
- Sum Assured must be between ₹1,00,000 and ₹5,00,00,000.
- Minimum Annual Premium is ₹5,000.
- PAN is mandatory if Annual Premium exceeds ₹50,000.
- Nominee is mandatory and cannot be the same as the customer.
- Payment Frequency must be valid.

---

## REST APIs

### Customer APIs

POST /customers

GET /customers

GET /customers/{id}

PUT /customers/{id}

---

### Proposal APIs

POST /proposals

GET /proposals/{id}

POST /proposals/{id}/submit

---

### Reference Master

GET /reference-master/POLICY_TERM

GET /reference-master/PAYMENT_FREQUENCY

---

### Audit

GET /audits

---

## Running the Project

Clone the project

```
mvn clean install
```

Run

```
mvn spring-boot:run
```

Application runs on

```
http://localhost:8080
```

---

## Testing APIs

APIs can be tested using

- VS Code REST Client
- cURL
- Postman
- Insomnia

---

## Author

Prajwal Madivalar