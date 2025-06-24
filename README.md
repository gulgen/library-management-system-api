
# 📚 Library Management System

This project is a **Library Management System** REST API developed using Java Spring Boot. It connects to a PostgreSQL database and manages books, authors, publishers, categories, and book borrowing operations.

## 🚀 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL
- Hibernate Validator (jakarta.validation)
- Maven

## 🧱 Layered Architecture

The project follows a layered architecture including:

- **Entity**: Database models
- **Repository**: JPA repository interfaces
- **Service**: Business logic layer
- **Controller**: REST API endpoints
- **DTO**: Data Transfer Objects
- **Exception**: Global exception handling

## 🗂️ Features

- CRUD operations for Books
- CRUD operations for Authors
- CRUD operations for Publishers
- CRUD operations for Categories (deletion restricted if associated books exist)
- Book borrowing functionality (with stock control)
- Update return date for borrowed books
- Input validation and global error handling
- DTO usage for selective field exposure

## 🛠️ Running the Project

1. Start and configure your PostgreSQL database.
2. Update `application.properties` or `application.yml` with your DB credentials.
3. Run the project using Maven:

```bash
./mvnw spring-boot:run
```

## 📑 API Endpoints

For a detailed list of all available API endpoints, see the following `.md` file:

👉 [API Endpoint Table](library-api-endpoints.md)

## 📌 Custom Business Rules

- Category deletion is blocked if books are associated with it.
- Borrowing is not allowed if book stock is 0.
- In Publisher GET responses, the `address` field is omitted via DTO.
- In BookBorrowing POST requests, the `email` field is not required in the DTO.

---
# 📖 Library Management System - API Endpoint Documentation

## 📚 Book Endpoints

| HTTP Method | URL                | Description                    | Request Body        | Response DTO         |
|-------------|--------------------|--------------------------------|---------------------|----------------------|
| GET         | `/api/books`       | Retrieve all books             | -                   | `List<BookResponse>` |
| GET         | `/api/books/{id}`  | Retrieve a book by ID          | -                   | `BookResponse`       |
| POST        | `/api/books`       | Add a new book                 | `BookRequest`       | `BookResponse`       |
| PUT         | `/api/books/{id}`  | Update a book                  | `BookUpdateRequest` | `BookResponse`       |
| DELETE      | `/api/books/{id}`  | Delete a book                  | -                   | `String` message     |

## ✍️ Author Endpoints

| HTTP Method | URL                  | Description              | Request Body      | Response DTO          |
|-------------|----------------------|--------------------------|-------------------|------------------------|
| GET         | `/api/authors`       | Retrieve all authors     | -                 | `List<AuthorResponse>` |
| GET         | `/api/authors/{id}`  | Retrieve an author by ID | -                 | `AuthorResponse`       |
| POST        | `/api/authors`       | Add a new author         | `AuthorRequest`   | `AuthorResponse`       |
| PUT         | `/api/authors/{id}`  | Update an author         | `AuthorRequest`   | `AuthorResponse`       |
| DELETE      | `/api/authors/{id}`  | Delete an author         | -                 | `String` message       |

## 🗂️ Category Endpoints

| HTTP Method | URL                      | Description                                         | Request Body        | Response DTO           |
|-------------|--------------------------|-----------------------------------------------------|---------------------|------------------------|
| GET         | `/api/categories`        | Retrieve all categories                             | -                   | `List<CategoryResponse>` |
| GET         | `/api/categories/{id}`   | Retrieve a category by ID                           | -                   | `CategoryResponse`       |
| POST        | `/api/categories`        | Add a new category                                  | `CategoryRequest`   | `CategoryResponse`       |
| DELETE      | `/api/categories/{id}`   | Delete a category (only if no books are associated) | -                   | `String` message         |

## 🏢 Publisher Endpoints

| HTTP Method | URL                    | Description                         | Request Body        | Response DTO           |
|-------------|------------------------|-------------------------------------|---------------------|------------------------|
| GET         | `/api/publishers`      | Retrieve all publishers             | -                   | `List<PublisherResponse>` |
| GET         | `/api/publishers/{id}` | Retrieve a publisher by ID          | -                   | `PublisherResponse`       |
| POST        | `/api/publishers`      | Add a new publisher                 | `PublisherRequest`  | `PublisherResponse`       |
| PUT         | `/api/publishers/{id}` | Update a publisher                  | `PublisherRequest`  | `PublisherResponse`       |
| DELETE      | `/api/publishers/{id}` | Delete a publisher                  | -                   | `String` message          |

## 🔁 Book Borrowing Endpoints

| HTTP Method | URL                          | Description                                               | Request Body                  | Response DTO              |
|-------------|------------------------------|-----------------------------------------------------------|-------------------------------|---------------------------|
| GET         | `/api/borrowings`            | Retrieve all book borrow records                         | -                             | `List<BookBorrowingResponse>` |
| GET         | `/api/borrowings/{id}`       | Retrieve a book borrowing by ID                          | -                             | `BookBorrowingResponse`       |
| POST        | `/api/borrowings`            | Borrow a book (only if stock is available)               | `BookBorrowingRequest`        | `BookBorrowingResponse`       |
| PUT         | `/api/borrowings/{id}`       | Update the return date of a borrowed book                | `BookBorrowingUpdateRequest`  | `BookBorrowingResponse`       |


This project is developed for educational purposes.
