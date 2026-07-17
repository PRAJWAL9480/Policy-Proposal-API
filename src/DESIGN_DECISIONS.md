# Design Decisions

# Policy Proposal Processing API

## 1. Architecture

The project follows a layered architecture to maintain separation of concerns.

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
In-Memory Storage (ConcurrentHashMap)
```

### Controller Layer

Responsible for handling HTTP requests and responses.

Responsibilities:

- Accept API requests
- Validate request body
- Call Service Layer
- Return Response DTOs

---

### Service Layer

Contains all business logic.

Responsibilities:

- Business validations
- Policy number generation
- Customer validation
- Proposal submission
- Audit creation

---

### Repository Layer

Responsible for storing application data.

The assignment specifies that no database should be used.

Therefore Java Collections are used.

Implementation:

- ConcurrentHashMap

Advantages:

- Thread-safe
- Fast lookup
- Simple implementation
- Suitable for assignment requirements

---

## 2. DTO Design

DTOs are used to separate API requests and responses from internal model classes.

Benefits:

- Hide internal fields
- Better API design
- Easy validation
- Easier future extension

Request DTOs

- CustomerRequest
- ProposalRequest

Response DTOs

- CustomerResponse
- ProposalResponse

---

## 3. Validation Strategy

Validation is performed at two levels.

### API Validation

Uses Jakarta Validation annotations.

Examples

- @NotBlank
- @NotNull
- @Email
- @Min
- @Max

---

### Business Validation

Implemented inside the Service Layer.

Business Rules

- Customer age must be between 18 and 65.
- Policy term must be valid.
- Premium must be greater than ₹5000.
- PAN required for premium greater than ₹50000.
- Nominee cannot be customer.
- Payment Frequency must exist.

---

## 4. Exception Handling

Custom exceptions are used.

Examples

- CustomerNotFoundException
- ProposalNotFoundException
- BusinessValidationException

Benefits

- Clean code
- Centralized error handling
- Meaningful error messages

---

## 5. Policy Number Generation

Policy numbers are generated using AtomicInteger.

Example

```
POL100001
POL100002
POL100003
```

AtomicInteger ensures unique sequential IDs during application execution.

---

## 6. Audit Design

Every proposal submission creates an audit record.

Audit stores

- Audit ID
- Proposal ID
- Policy Number
- Action
- Timestamp

Purpose

- Traceability
- History of proposal processing

---

## 7. Thread Safety

ConcurrentHashMap is used for all repositories.

Advantages

- Safe for concurrent access
- Better than HashMap for multi-threaded applications

---

## 8. Assumptions

- Data exists only while the application is running.
- IDs are generated in memory.
- No authentication required.
- No database persistence.

---

## 9. Trade-offs

Advantages

- Simple architecture
- Easy to understand
- Fast development
- Assignment compliant

Limitations

- Data is lost after restart.
- No database persistence.
- No authentication.
- No caching.

---

## 10. Future Enhancements

- Spring Data JPA
- MySQL/PostgreSQL
- Swagger/OpenAPI
- Spring Security
- JWT Authentication
- Docker
- Unit & Integration Testing
- Cloud Deployment

---

## Conclusion

The project follows a clean layered architecture with clear separation of concerns.

Business logic is isolated in the Service layer, data access is handled through repositories using thread-safe Java Collections, and REST APIs are exposed through controllers.

The implementation satisfies the assignment requirements while remaining easy to understand, maintain, and extend.
