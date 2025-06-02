# FinApp — Personal Finance Management Backend

## Overview

FinApp is a backend service for a personal finance management application.  
It is built with Java Spring Boot and uses PostgreSQL as the database.  
Docker Compose is used to run the database and pgAdmin for management.

The backend supports features like user authentication, budgeting, transaction management, and AI-powered financial advice.

---

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL

---

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/finapp.git
    cd finapp
    ```

2. **Create a `.env` file** in the project root with the following environment variables (update values accordingly):

    ```env
    SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/finappdb
    SPRING_DATASOURCE_USERNAME=your_db_user
    SPRING_DATASOURCE_PASSWORD=your_db_password

    POSTGRES_USER=your_db_user
    POSTGRES_PASSWORD=your_db_password
    POSTGRES_DB=finappdb

    PGADMIN_DEFAULT_EMAIL=your_email@example.com
    PGADMIN_DEFAULT_PASSWORD=your_pgadmin_password

    GROQ_API_KEY=your_groq_api_key
    ```

3. **Run Docker containers for PostgreSQL and pgAdmin:**

    ```bash
    docker-compose up -d
    ```

4. **Build and run the backend:**

    ```bash
    ./mvnw clean package
    java -jar target/finapp.jar
    ```

---

## Project Structure

- `src/main/java` — backend source code (Spring Boot)
- `src/main/resources` — configuration files and SQL scripts
- `src/main/resources/queries` — custom SQL queries used by the application
- `src/main/resources/migration` — database migration scripts
- `compose.yaml` — Docker Compose file for PostgreSQL and pgAdmin
- `.env` — environment variables (excluded from git)

---

## SQL Queries and Database Migrations

- All custom SQL queries used by the application are stored in the `src/main/resources/queries` folder.
- Database migration scripts for creating and updating tables are located in the `src/main/resources/migration` directory.
---

## Contact

For questions or support, contact: yulia.yakupova@gmail.com
