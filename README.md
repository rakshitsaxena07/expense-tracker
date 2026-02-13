# Problem Statement:
Expense Tracker Build a RESTful API for a Personal Expense Tracker that allows a user to record and manage their expenses. All data must be stored in a database.

# Expense Tracker API
A RESTful API for managing personal expenses built using Java Spring Boot.
All expense data is persisted in a relational database (MySQL), with support for unit testing using H2 in-memory database.

## Setup

### Prerequisites

* Java 21+ 
* Gradle
* MySQL running locally

### Configure Database

Create a database in MySQL:

```sql
CREATE DATABASE expense_tracker_demo;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker_demo
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Run Application

```bash
./gradlew bootRun
```

Server will start at:

```
http://localhost:8080
```

### Run Tests (uses H2 in-memory DB)

```bash
./gradlew test
```
## Architecture

* Controller Layer → Handles HTTP requests
* Service Layer → Business logic
* Repository Layer → Database access (JPA)
* Entity Layer → Domain models

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* MySQL (main database)
* H2 (for unit tests)
* Gradle
* JUnit & Mockito (unit testing)
