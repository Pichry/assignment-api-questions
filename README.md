#Niyirema sabato clesence 
## ID:27653
### assignment 2
--------------------------
# Spring Boot RESTful API Practical Assignment

This repository contains multiple **Spring Boot RESTful APIs** developed for practical exercises in building REST controllers. Each question is implemented as a separate project following best practices, proper HTTP methods, and status codes. The APIs are tested using Postman and can be run locally.  

---

## Table of Contents

1. [Project Overview](#project-overview)  
2. [Project Structure](#project-structure)  
3. [API Endpoints](#api-endpoints)  
4. [How to Run](#how-to-run)  
5. [Testing and Screenshots](#testing-and-screenshots)  
6. [Notes](#notes)  

---

## Project Overview

This assignment consists of **six distinct APIs**, covering a range of real-world use cases:

- **Library Book Management** – Manage books in a library  
- **Student Registration** – Register and filter student data  
- **Restaurant Menu Management** – Manage restaurant menu items  
- **E-Commerce Product Catalog** – Product management with filters and stock  
- **Task Management** – Manage to-do tasks with priority and completion  
- **User Profile Management (Bonus)** – Full CRUD with search, activation, and custom response  

Each API demonstrates the ability to handle **CRUD operations, filtering, and query parameters** without a database (data is managed in-memory using Lists).  

---

## Project Structure

| Question | Folder | Description |
|----------|--------|-------------|
| 1        | question1-library-api | Library Book Management API |
| 2        | question2-student-api | Student Registration API |
| 3        | question3-restaurant-api | Restaurant Menu API |
| 4        | question4-ecommerce-api | E-Commerce Product API |
| 5        | question5-task-api | Task Management API |
| Bonus    | bonus-user-profile-api | User Profile Management API |

Each project contains two main packages:  

- `model` – Contains entity classes (e.g., `Book`, `Student`)  
- `controller` – Contains REST controllers with endpoints for CRUD and filtering  

---

## API Endpoints

### Question 1 – Library Book Management API
- `GET /api/books` – Retrieve all books  
- `GET /api/books/{id}` – Retrieve a book by its ID  
- `GET /api/books/search?title={title}` – Search books by title  
- `POST /api/books` – Add a new book  
- `DELETE /api/books/{id}` – Delete a book by ID  

### Question 2 – Student Registration API
- `GET /api/students` – List all students  
- `GET /api/students/{studentId}` – Get student by ID  
- `GET /api/students/major/{major}` – Filter students by major  
- `GET /api/students/filter?gpa={minGpa}` – Filter students by GPA  
- `POST /api/students` – Add a new student  
- `PUT /api/students/{studentId}` – Update student information  
- `DELETE /api/students/{studentId}` – Delete student  

### Question 3 – Restaurant Menu API
- `GET /api/menu` – List all menu items  
- `GET /api/menu/{id}` – Get menu item by ID  
- `GET /api/menu/category/{category}` – Filter menu items by category  
- `GET /api/menu/available?available=true` – Get only available items  
- `GET /api/menu/search?name={name}` – Search items by name  
- `POST /api/menu` – Add a new menu item  
- `PUT /api/menu/{id}/availability` – Toggle item availability  
- `DELETE /api/menu/{id}` – Remove a menu item  

### Question 4 – E-Commerce Product API
- `GET /api/products` – List all products (supports pagination `?page=&limit=`)  
- `GET /api/products/{productId}` – Get product by ID  
- `GET /api/products/category/{category}` – Filter by category  
- `GET /api/products/brand/{brand}` – Filter by brand  
- `GET /api/products/search?keyword={keyword}` – Search products by keyword  
- `GET /api/products/price-range?min=&max=` – Filter products by price range  
- `GET /api/products/in-stock` – Products with stock > 0  
- `POST /api/products` – Add a new product  
- `PUT /api/products/{productId}` – Update product details  
- `PATCH /api/products/{productId}/stock?quantity=` – Update stock quantity  
- `DELETE /api/products/{productId}` – Delete a product  

### Question 5 – Task Management API
- `GET /api/tasks` – List all tasks  
- `GET /api/tasks/{taskId}` – Get task by ID  
- `GET /api/tasks/status?completed=true/false` – Filter tasks by completion status  
- `GET /api/tasks/priority/{priority}` – Filter tasks by priority  
- `POST /api/tasks` – Add a new task  
- `PUT /api/tasks/{taskId}` – Update task details  
- `PATCH /api/tasks/{taskId}/complete` – Mark task as completed  
- `DELETE /api/tasks/{taskId}` – Delete a task  

### Bonus – User Profile API
- Full CRUD for user profiles  
- Search by username, country, or age range  
- Activate or deactivate profiles  
- All responses wrapped in a custom `ApiResponse` object for consistent messaging  

---

## How to Run

1. Clone the repository:
```bash
[](https://github.com/Pichry/assignment-api-questions.git)
