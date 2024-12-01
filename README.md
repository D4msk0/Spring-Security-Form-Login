[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/PkCmG2MpfacdRogv3iFf3i/NpS1xuRavXjGHE2vbD794f/tree/main.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/circleci/PkCmG2MpfacdRogv3iFf3i/NpS1xuRavXjGHE2vbD794f/tree/main)

# Spring Security Form Login Example

This is a simple Spring Boot application demonstrating **Spring Security Form Login** with **Thymeleaf**, **Maven**, and **Java 21**.

## Features
- User authentication with in-memory credentials.
- Custom login page with form-based login.
- Redirects to a home page after successful login.
- Simple logout functionality with a success URL.

## Project Structure
src/main/java/org/jcourant/springsecurityformlogin 
- ├── config // Security configuration 
- ├── controller // Controllers for handling web requests 
- ├── model // Entity classes for database mapping (User) 
- ├── repository // Database repositories (UserRepository) 
- └── LoginAppApplication.java // Main Spring Boot application class

src/main/resources 
- ├── static // Static resources like CSS, JS 
- ├── templates // Thymeleaf templates (login.html, home.html) 
- └── application.properties // Configuration file


## Prerequisites
- Java 21
- Maven

## Setup

1. **Clone the repository:**
    ```bash
    git clone https://github.com/D4msk0/Spring-Security-Form-Login.git
    cd login-app
    ```

2. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

3. **Access the application:**
   Open your browser and go to:
    - Login Page: `http://localhost:8080/login`
    - Home Page (after login): `http://localhost:8080/home`

   **Credentials:**
    - Username: `user`
    - Password: `password`

## Key Files
- **`SecurityConfig.java`**: Configures Spring Security, login page, and logout behavior.
- **`LoginController.java`**: Handles login and home page requests.
- **`login.html`**: Thymeleaf template for the login page.
- **`home.html`**: Thymeleaf template for the home page after login.

## Customization
- Modify user credentials in `SecurityConfig.java` to match your requirements.
- Customize login page (`login.html`) and home page (`home.html`) as needed.

## License
This project is licensed under the MIT License.


## To Do
- Add Tests
- Enhance the Application
- Replace in-memory authentication with database-backed authentication by integrating UserRepository.
- Add role-based access controls.
- Use CSS to style your pages in the static folder.
