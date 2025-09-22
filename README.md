#Bajaj Spring Boot Application
Overview

This is a Spring Boot application that automates a task flow for generating a webhook, solving a SQL problem, and submitting the solution. The application runs automatically on startup without any REST endpoints.

Features

Sends a POST request to generate a webhook on application startup.

Determines which SQL problem to solve based on the last digit of the registration number.

Stores and solves the SQL problem.

Submits the final SQL query to the webhook URL using a JWT token for authorization.

Fully automated using CommandLineRunner.

Project Structure
/Bajaj
 └── /Bajaj
     ├── HELP.md                  # This README
     ├── mvnw                     # Maven wrapper script (Linux/Mac)
     ├── mvnw.cmd                 # Maven wrapper script (Windows)
     ├── pom.xml                  # Maven configuration
     ├── src/main/java/com/example/App/
     │    └── BajajApplication.java       # Main Spring Boot application
     ├── src/main/resources/
     │    └── application.properties      # Spring Boot properties
     ├── src/test/java/com/example/App/
     │    ├── BajajApplicationTests.java # Unit tests
     │    ├── model/GenerateWebhookResponse.java
     │    ├── model/UserRequest.java
     │    ├── RestConfig.java
     │    └── runner/StartupRunner.java  # Startup logic runner
     └── target/                   # Compiled JAR and classes
