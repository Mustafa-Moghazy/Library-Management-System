# 📚 Library Management System (Spring Boot)

This is a **Library Management System API** built with **Spring Boot**, **Spring Data JPA**, and **RESTful APIs**.  
The project demonstrates how to manage books, authors, publishers, categories, users, borrowing transactions, and user activities in a clean and modular way using DTOs, services, repositories, and controllers.

---

## 🚀 Features

- **Books Management**
  - Create, update, delete, and fetch books
  - Search books by title, ISBN, author, or publisher
  - Pagination and sorting support

- **Authors Management**
  - Manage authors and their books
  - Get all books written by an author

- **Publishers Management**
  - Manage publishers and their books

- **Categories Management**
  - Manage categories and the books under them

- **Users & Roles**
  - Manage users with roles
  - Assign roles to users (stored as strings)

- **Borrow Transactions**
  - Borrow books (track user and book details)
  - Get all borrow transactions

- **User Activities**
  - Track user actions (borrow, return, etc.)
  - Get activities for a specific user

---

## 📖 API Endpoints

### 🔹 Books (`/api/books`)
- `POST /api/books` → Create a new book
- `GET /api/books` → Get all books
- `GET /api/books/page` → Get paginated & sorted books
- `GET /api/books/{id}` → Get book by ID
- `GET /api/books/title/{title}` → Get book by title
- `GET /api/books/isbn/{isbn}` → Get book by ISBN
- `GET /api/books/publisher/{publisherName}` → Get books by publisher
- `GET /api/books/author/{authorName}` → Get books by author
- `PUT /api/books/{id}` → Update a book
- `DELETE /api/books/{id}` → Delete a book

### 🔹 Authors (`/api/authors`)
- `POST /api/authors` → Create author
- `GET /api/authors` → Get all authors
- `GET /api/authors/{id}` → Get author by ID
- `GET /api/authors/{id}/books` → Get books by author
- `PUT /api/authors/{id}` → Update author
- `DELETE /api/authors/{id}` → Delete author

### 🔹 Categories (`/api/categories`)
- `POST /api/categories` → Create category
- `GET /api/categories` → Get all categories
- `GET /api/categories/{id}` → Get category by ID
- `GET /api/categories/{id}/books` → Get books in a category
- `PUT /api/categories/{id}` → Update category
- `DELETE /api/categories/{id}` → Delete category

### 🔹 Publishers (`/api/publishers`)
- `POST /api/publishers` → Create publisher
- `GET /api/publishers` → Get all publishers
- `GET /api/publishers/{id}` → Get publisher by ID
- `GET /api/publishers/{id}/books` → Get books by publisher
- `PUT /api/publishers/{id}` → Update publisher
- `DELETE /api/publishers/{id}` → Delete publisher

### 🔹 Users (`/api/users`)
- `POST /api/users` → Create user
- `GET /api/users` → Get all users
- `GET /api/users/{id}` → Get user by ID
- `DELETE /api/users/{id}` → Delete user

### 🔹 Roles (`/api/roles`)
- `POST /api/roles` → Create role
- `GET /api/roles` → Get all roles

### 🔹 Borrow Transactions (`/api/borrow`)
- `POST /api/borrow?userId={userId}&bookId={bookId}` → Borrow book
- `GET /api/borrow` → Get all borrow transactions

### 🔹 User Activities (`/api/activities`)
- `GET /api/activities` → Get all activities
- `GET /api/activities/user/{userId}` → Get activities for a user

---

