# Authentication with JWT and Spring Boot

This project is a backend service for authentication and session management using Spring Boot. It leverages JWT (JSON Web Tokens) for securing resources and managing user sessions, ensuring a robust and scalable authentication mechanism.

## Features

- **User Authentication**:
  - Securely sign up new users.
  - Log in with valid credentials to receive a JWT token.
- **JWT-Based Authorization**:
  - Protect endpoints with token-based access control.
  - Use the provided JWT token for subsequent authenticated requests.
- **Database Integration**:
  - User data is persisted in a MySQL database.
- **Spring Security Integration**:
  - Implemented authentication and authorization using Spring Security.

## Technologies Used

- **Spring Boot**
- **Spring Security**
- **Java 17**
- **JWT (JSON Web Tokens)**
- **MySQL**
- **Maven**

## Installation

### Prerequisites
1. Install [Java JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Install [Maven](https://maven.apache.org/) for managing dependencies.
3. Install [MySQL](https://www.mysql.com/) and set up a database for the project.

### Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/auth-with-jwt.git
   cd auth-with-jwt
   ```

2. Configure the database:
   - Update the `application.properties` file with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```
   > **Note**: For production environments, use environment variables for sensitive data like database credentials. Refer to [Spring Boot External Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config) for guidance.

3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.

## API Endpoints

### Authentication

#### Sign Up
- **Endpoint**: `POST /api/v1/auth/register`
- **Description**: Register a new user.
- **Request Body**:
  ```json
  {
    "email": "user@example.com",
    "password": "securePassword"
  }
  ```

#### Log In
- **Endpoint**: `POST /api/v1/auth/login`
- **Description**: Authenticate with valid credentials and receive a JWT token.
- **Request Body**:
  ```json
  {
    "email": "user@example.com",
    "password": "securePassword"
  }
  ```
- **Response**:
  ```json
  {
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

#### Verify Token
- **Endpoint**: `POST /api/v1/auth/verifyToken`
- **Description**: Verify if the token is valid.
- **Request Body**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  }
  ```

### Protected Resources

#### Access Secured Endpoint
- **Endpoint**: `GET /api/v1/home`
- **Description**: Access a resource protected by JWT authentication.
- **Headers**:
  ```json
  {
    "Authorization": "Bearer <your-jwt-token>"
  }
  ```

## Future Enhancements

- Add password reset functionality.
- Implement email verification during sign-up.
- Add refresh token support for extended session management.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For questions or collaborations, reach out at [nkopane.mj@gmail.com].

