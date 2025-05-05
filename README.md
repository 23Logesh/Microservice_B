# 📦 Service_B Microservice

A Spring Boot-based microservice that:

- Consumes messages from Apache Kafka and saves them to an H2 in-memory database.
- Sends messages to Service_A via REST using RestTemplate.
- Exposes REST APIs with auto-generated Swagger documentation.

---

## 🚀 Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA (H2 in-memory DB)  
- Apache Kafka  
- RestTemplate  
- Springdoc OpenAPI (Swagger UI)  
- Lombok  
- ModelMapper  
- SLF4J Logging  

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.example.Service_B/
│   │       ├── controller/
│   │       ├── consumer/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── serviceImpl/
│   │       ├── serviceInter/
│   │       └── utility/
│   └── resources/
│       └── application.yml
```

---

## ⚙️ Configuration (`application.yml`)

```yaml
server:
  port: 8084

spring:
  application:
    name: Service_B

  datasource:
    url: jdbc:h2:mem:ass2db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
        format_sql: true

  h2:
    console:
      enabled: true
      path: /h2-console

  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: my-group
      auto-offset-reset: earliest
      key-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
      properties:
        spring.deserializer.key.delegate.class: org.apache.kafka.common.serialization.StringDeserializer
        spring.deserializer.value.delegate.class: org.springframework.kafka.support.serializer.JsonDeserializer
        spring.json.trusted.packages: '*'
```

---

## 🛠️ Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/23Logesh/Microservice_B.git
cd Microservice_B
```

### 2. Start Kafka (locally or via Docker)
If you're using Docker:
```bash
docker-compose up -d
```

> Ensure Kafka is running on `localhost:9092`.

### 3. Run the Spring Boot application
Using Maven:
```bash
./mvnw spring-boot:run
```

---

## 📑 API Documentation (Swagger UI)

Once the application is running, access the Swagger documentation:

🌐 [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)

---

## 🧪 H2 Database Console

Inspect the database directly via the H2 console:

🌐 [http://localhost:8084/h2-console](http://localhost:8084/h2-console)

- **JDBC URL**: `jdbc:h2:mem:ass2db`
- **Username**: `sa`
- **Password**: _(leave blank)_

---

## 🧾 Sample APIs

### ✅ Send a message from Service_B to Service_A
```http
POST /sendBToA
Content-Type: application/json

{
  "message": "Hello from Service_B"
}
```

### ✅ Get all messages stored in Service_B DB
```http
POST /getAllMessage
```

---

## 📝 Logging Pattern

Logs follow this format:
```
[SERVICE_B] - [MODULE] - [ACTION] - [STATUS]
```

Example:
```
[SERVICE_B] - [KAFKA] - Received message from topic: ServerA-topic - [SUCCESS]
[SERVICE_B] - [REST] - Sent message to Service_A - [SUCCESS]
```

---

## ✅ Features

- Kafka consumer receives messages and stores them in DB.
- Sends message to Service_A through REST.
- Uses Swagger for API documentation.
- Uses in-memory H2 DB with a visual console.
- Structured responses using `ResponseStructure<T>`.

---

## 🔁 CI/CD

- **CI Jenkinsfile**: Builds the project, runs tests, and creates a Docker image.  
- **CD Jenkinsfile**: Pulls the image and deploys the microservice via Docker.

---

## 📬 Contact

For questions or help, contact [23logeshwaran@gmail.com].
