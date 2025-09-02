# Internship Management Portal - Microservices Architecture

A comprehensive internship management system built with microservices architecture using Spring Boot, designed to streamline the entire internship lifecycle from application to completion.

## 🏗️ Architecture Overview

This project follows a microservices architectural pattern with the following services:

- **Discovery Server** (Eureka) - Service discovery and registration
- **Auth Service** - Authentication and authorization with JWT
- **User Service** - User management (Interns, Mentors, Admins)
- **Internship Service** - Internship opportunity management
- **Application Service** - Application processing and tracking
- **Task Service** - Task assignment and management
- **Submission Service** - Submission handling and evaluation

## 🚀 Features

### Core Functionality
- **User Management**: Role-based access control (Intern, Mentor, Admin)
- **Internship Management**: Create, update, and manage internship opportunities
- **Application System**: Apply, track, and manage internship applications
- **Task Management**: Assign and track tasks for interns
- **Submission System**: Handle and evaluate intern submissions
- **Authentication**: JWT-based secure authentication
- **Service Discovery**: Automatic service registration and discovery

### Technical Features
- **Microservices Architecture**: Loosely coupled, independently deployable services
- **Docker Support**: Containerized deployment for all services
- **Service Communication**: Inter-service communication via OpenFeign
- **Load Balancing**: Built-in load balancing capabilities
- **Database**: MySQL with JPA/Hibernate
- **Security**: Spring Security with JWT tokens

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.9+
- MySQL 8.0+
- Docker (optional)

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd Internship-Management-Portal-Microservices
```

### 2. Database Setup
Create MySQL databases for each service:
```sql
CREATE DATABASE user_db;
CREATE DATABASE internship_db;
CREATE DATABASE application_db;
CREATE DATABASE task_db;
CREATE DATABASE submission_db;
```

### 3. Environment Configuration
Create `.env` files in each service directory with your database credentials:
```env
DB_URL=jdbc:mysql://localhost:3306/<database_name>?useUnicode=true&characterEncoding=utf8
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

### 4. Service Ports
Each service runs on a different port:
- Discovery Server: 8761
- Auth Service: 8083
- User Service: 8087
- Internship Service: 8082
- Application Service: 8084
- Task Service: 8081
- Submission Service: 8085

### 5. Running the Services

#### Option 1: Individual Service Startup
Start services in the following order:

1. **Discovery Server** (must be started first):
```bash
cd discovery_server
mvn spring-boot:run
```

2. **Auth Service**:
```bash
cd auth_service
mvn spring-boot:run
```

3. **User Service**:
```bash
cd user_service
mvn spring-boot:run
```

4. **Other services** (in any order):
```bash
cd internship_service
mvn spring-boot:run

cd application_service
mvn spring-boot:run

cd task_service
mvn spring-boot:run

cd submission_service
mvn spring-boot:run
```

#### Option 2: Docker Deployment
Build and run services using Docker:

```bash
# Build all services
docker-compose build

# Run all services
docker-compose up
```

## 🔧 Service Details

### Discovery Server
- **Port**: 8761
- **Purpose**: Service discovery and registration
- **URL**: http://localhost:8761

### Auth Service
- **Port**: 8083
- **Purpose**: Authentication, authorization, and JWT token management
- **Features**: User registration, login, JWT token generation

### User Service
- **Port**: 8087
- **Purpose**: User management and role-based access control
- **Features**: CRUD operations for users, role management

### Internship Service
- **Port**: 8082
- **Purpose**: Internship opportunity management
- **Features**: Create, update, list internships

### Application Service
- **Port**: 8084
- **Purpose**: Application processing and tracking
- **Features**: Apply for internships, track application status

### Task Service
- **Port**: 8081
- **Purpose**: Task assignment and management
- **Features**: Create tasks, assign to interns, track progress

### Submission Service
- **Port**: 8085
- **Purpose**: Submission handling and evaluation
- **Features**: Submit work, evaluate submissions


## 📡 API Documentation

Each service exposes REST APIs. Key endpoints include:

### Auth Service
- `POST /auth/register` - User registration
- `POST /auth/login` - User login
- `POST /auth/validate` - Token validation

### User Service
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `POST /users` - Create user
- `PUT /users/{id}` - Update user

### Internship Service
- `GET /internships` - Get all internships
- `POST /internships` - Create internship
- `GET /internships/{id}` - Get internship by ID


## 📁 Project Structure

```
Internship-Management-Portal-Microservices/
├── discovery_server/          # Eureka discovery server
├── auth_service/              # Authentication service
├── user_service/              # User management service
├── internship_service/        # Internship management service
├── application_service/       # Application processing service
├── task_service/              # Task management service
├── submission_service/        # Submission handling service
└── README.md
