🚀 CareerSync - Job Portal Application

📌 Project Overview

CareerSync is a cloud-based Job Portal web application built using
Spring Boot. It allows users to search and apply for jobs, while
administrators manage job listings and users securely.

🏗 Architecture

Controller → Service → Repository → Database

🛠 Tech Stack

Backend: Spring Boot 4
Security: Spring Security 🔐
ORM: Spring Data JPA
Database: MySQL 🗄
Build Tool: Maven
Java Version: OpenJDK 24 ☕
Template Engine: Thymeleaf

📂 Project Structure

com.career.CareerSync

Controllers: - AuthController - UserController - JobController -
HomeController

Services: - JobServices

Repositories: - UserRepository - JobRepository - AdminRepository -
SecurityRepository

Models: - MyUser - Job - Role - JobType

Security: - SecurityConfig - MyUserDetailsService

🔐 Authentication & Authorization

CareerSync uses Spring Security with: - DaoAuthenticationProvider -
BCryptPasswordEncoder 🔒 - Custom UserDetailsService - Role-Based
Authorization

👥 Roles: - ROLE_ADMIN 🛠 - ROLE_USER 👤

Admin Access: - Manage jobs - View users

User Access: - Search jobs 🔎 - Save jobs 💾 - Apply for jobs 📄

🗄 Database Configuration (application.properties)

spring.datasource.url=jdbc:mysql://localhost:3306/careersync
spring.datasource.username=root spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

▶ How to Run

1.  Clone the repository git clone
    https://github.com/Ramothopo-MD/CareerSync.git

2.  Build the project mvn clean install

3.  Run the application mvn spring-boot:run

🐳 Docker MySQL Example

docker run –name careersync-db -e MYSQL_ROOT_PASSWORD=yourpassword -e
MYSQL_DATABASE=careersync -p 3306:3306 -d mysql:latest

🚀 Future Improvements

-   Email notifications 📧
-   Resume upload support 📎
-   REST API for mobile app 📱
-   Pagination
-   JWT Authentication 🔑
-   Docker Compose
-   CI/CD pipeline ⚙

👨‍💻 Author

Mosewa Desmond
Java Backend Developer

📄 License

Educational use only.
