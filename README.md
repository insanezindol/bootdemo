# BootDemo

A comprehensive Spring Boot demonstration project showcasing various enterprise technologies and patterns including web services, data persistence, caching, messaging, security, and more.

## 🚀 Overview

This project demonstrates a full-stack Spring Boot application with multiple integrations and best practices for modern Java web development. It includes examples of RESTful APIs, database operations, caching strategies, message queuing, authentication, and scheduled tasks.

## 🔧 Technology Stack

### Framework & Runtime

-   **Java**: 1.8
-   **Spring Boot**: 2.1.7.RELEASE
-   **Spring Security**: Authentication & Authorization
-   **Spring AOP**: Aspect-Oriented Programming
-   **Maven**: Dependency Management & Build Tool

### Databases

-   **MySQL**: Primary relational database
-   **MongoDB**: NoSQL document database
-   **Redis**: In-memory cache and session store

### Messaging & Communication

-   **RabbitMQ**: Message broker for asynchronous communication
-   **HTTP Client**: Apache HttpComponents for external API calls
-   **Email**: SMTP integration for mail services

### Data Access & Processing

-   **MyBatis**: SQL mapping framework
-   **Spring Data JPA**: Database abstraction
-   **Spring Data MongoDB**: MongoDB integration
-   **Spring Data Redis**: Redis operations

### Development & Monitoring

-   **Lombok**: Boilerplate code reduction
-   **Log4jdbc**: SQL logging and monitoring
-   **Logback**: Logging framework
-   **JWT**: JSON Web Token for stateless authentication
-   **FastJSON**: JSON processing

## 📁 Project Structure

```
src/main/java/com/example/bootdemo/
├── config/           # Configuration classes
│   ├── WebSecurityConfig.java
│   ├── RedisConfig.java
│   ├── RabbitMQConfig.java
│   ├── DefaultDatabaseConfig.java
│   └── ...
├── controller/       # REST API endpoints
│   ├── AuthController.java
│   ├── PostsController.java
│   ├── EventController.java
│   ├── RedisController.java
│   ├── RabbitController.java
│   └── ...
├── service/          # Business logic layer
│   ├── AuthService.java
│   ├── PostsService.java
│   ├── EventService.java
│   ├── RedisService.java
│   └── ...
├── mapper/           # MyBatis mappers
├── vo/               # Value objects & DTOs
├── util/             # Utility classes
└── schedule/         # Scheduled tasks
```

## 🌟 Key Features

### 🔐 Authentication & Security

-   JWT-based stateless authentication
-   Spring Security integration
-   Custom authentication filters and entry points
-   Role-based access control

### 📊 Data Management

-   **MySQL**: Relational data with MyBatis ORM
-   **MongoDB**: Document storage for flexible data structures
-   **Redis**: Caching and session management
-   Transaction management and data validation

### 📨 Messaging & Communication

-   **RabbitMQ**: Asynchronous message processing
-   **Email Service**: SMTP integration with AWS SES
-   **HTTP Client**: External API integration utilities

### 📅 Scheduling & Background Tasks

-   Spring-based task scheduling
-   Configurable scheduled jobs
-   Background processing capabilities

### 🔍 Monitoring & Logging

-   Comprehensive logging with Logback
-   SQL query logging with Log4jdbc
-   AOP-based execution time monitoring
-   Custom exception handling

### 🧪 Testing & Documentation

-   HTTP test files for API testing
-   Unit test structure
-   RESTful API documentation through code

## ⚙️ Configuration

### Database Setup

1. **MySQL Configuration**

```properties
spring.datasource.url=jdbc:log4jdbc:mysql://127.0.0.1:3306/appdb
spring.datasource.username=appuser
spring.datasource.password=123456
```

2. **MongoDB Configuration**

```properties
spring.data.mongodb.uri=mongodb://127.0.0.1:27017
spring.data.mongodb.database=testdb
```

3. **Redis Configuration**

```properties
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

### Message Broker Setup

**RabbitMQ Configuration**

```properties
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=rabbitmq
spring.rabbitmq.password=rabbitpwd
```

## 🚀 Getting Started

### Prerequisites

-   Java 1.8 or higher
-   Maven 3.6+
-   MySQL 5.7+
-   Redis 5.0+
-   RabbitMQ 3.7+
-   MongoDB 4.0+ (optional)

### Installation & Setup

1. **Clone the repository**

```bash
git clone <repository-url>
cd bootdemo
```

2. **Database Setup**

```bash
# Create MySQL database
mysql -u root -p
CREATE DATABASE appdb;
CREATE USER 'appuser'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON appdb.* TO 'appuser'@'localhost';
```

3. **Start Required Services**

```bash
# Start Redis
redis-server

# Start RabbitMQ
rabbitmq-server

# Start MongoDB (if using MongoDB features)
mongod
```

4. **Build and Run**

```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📋 API Endpoints

### Authentication

-   `POST /auth/login` - User authentication
-   `POST /auth/logout` - User logout
-   `GET /auth/verify` - Token verification

### Posts Management

-   `GET /posts` - List all posts
-   `POST /posts` - Create new post
-   `PUT /posts/{id}` - Update post
-   `DELETE /posts/{id}` - Delete post

### Events

-   `GET /events` - Get event data
-   `POST /events` - Create event

### Redis Operations

-   `GET /redis/{key}` - Get cached data
-   `POST /redis` - Cache data

### RabbitMQ

-   `POST /rabbit/send` - Send message to queue
-   `GET /rabbit/receive` - Receive messages

## 🧪 Testing

The project includes HTTP test files located in `src/test/http/`:

-   `auth.http` - Authentication API tests
-   `posts.http` - Posts API tests
-   `event.http` - Events API tests
-   `redis.http` - Redis operations tests
-   `rabbitmq.http` - RabbitMQ messaging tests
-   `restful.http` - General REST API tests

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For questions and support, please open an issue in the GitHub repository.
