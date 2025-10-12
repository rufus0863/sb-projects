# RESTful Project

This project demonstrates the implementation of a **RESTful API** using standard design principles and best practices.  
It covers key concepts such as request handling, parameter management, and error handling, ensuring a clean and maintainable architecture.

- Controllers 
- Request mappings 
- Request parameters 
- Path variables 
- Reqeust body 
- Exception handling 
- API documentation
---

## 📘 Overview

The project provides a simple yet comprehensive example of how to build RESTful endpoints with controllers, request mappings, and proper documentation.  
It is designed to serve as a reference for developers learning or refining their REST API development skills.

---

## 🚀 Features

### 🧭 Controllers
- Controllers define and expose REST endpoints for various resources.
- Each controller handles incoming HTTP requests and delegates business logic to the appropriate services.

### 🔗 Request Mappings
- Uses annotations like `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping`.
- Demonstrates how to organize endpoints following RESTful conventions (e.g., `/api/products`, `/api/orders`).

### ⚙️ Request Parameters
- Handles query parameters via `@RequestParam`.
- Example:  
  ```java
  @GetMapping("/products")
  public List<Product> getProducts(@RequestParam(required = false) String category) { ... }
  ```

### 🧩 Path Variables
- Uses `@PathVariable` to extract dynamic values from the URL path.
- Example:  
  ```java
  @GetMapping("/products/{id}")
  public Product getProductById(@PathVariable Long id) { ... }
  ```

### 📦 Request Body
- Demonstrates deserialization of JSON payloads using `@RequestBody`.
- Example:  
  ```java
  @PostMapping("/products")
  public Product createProduct(@RequestBody Product product) { ... }
  ```

### ⚠️ Exception Handling
- Centralized exception handling with `@ControllerAdvice`.
- Returns structured error responses (status code, message, timestamp).
- Ensures consistent API behavior and better debugging.

### 📚 API Documentation
- API endpoints are documented using **OpenAPI/Swagger**.
- Provides an interactive web UI for testing API endpoints.
- Example configuration:  
  ```
  http://localhost:8080/swagger-ui/index.html
  ```

---

## 🧰 Technologies
- **Java 17+**
- **Spring Boot** (for REST API implementation)
- **Spring Web / Validation**
- **Swagger / OpenAPI 3**
- **Maven** (build management)

---

## 🏁 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/restful-project.git
   cd restful-project
   ```

2. **Build and run**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the API**
   ```
   http://localhost:8080/api
   ```

4. **View API documentation**
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 🧪 Example Endpoints

| Method | Endpoint              | Description             |
|--------|-----------------------|-------------------------|
| GET    | `/api/products`       | Get all products        |
| GET    | `/api/products/{id}`  | Get product by ID       |
| POST   | `/api/products`       | Create new product      |
| PUT    | `/api/products/{id}`  | Update product by ID    |
| DELETE | `/api/products/{id}`  | Delete product by ID    |

---

## 🧾 License
This project is licensed under the [MIT License](LICENSE).

---

**Author:** Nino Caputi (RUFUS0863)
**Version:** 1.0.0
