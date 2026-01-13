Graduation Project – Backend Web Application
Project Overview

This project was developed as a graduation project to consolidate backend development principles for modern web applications.
The main goal was to design and implement a layered backend architecture, work with RESTful APIs, databases, security, and containerization tools.

The project demonstrates practical experience with Java backend development, including API design, database interaction, application configuration, and deployment.

Functional Description

The application is a backend system for financial management, built using a microservice-oriented approach.
The system is divided into several independent services, each responsible for a specific domain area.

Implemented Services:

User Service – user management and authentication

Account Service – management of financial accounts

Classifier Service – reference and classification data

Audit Service – logging and auditing of system actions

Each service exposes RESTful APIs and communicates using standard HTTP mechanisms.

Architecture & Design

The project follows a layered MVC architecture:

Controller layer
Handles HTTP requests using Spring Web MVC and DispatcherServlet.

Service layer
Contains business logic and data transformations between DTOs and entities.

Data Access layer (DAO / Repository)
Interacts with PostgreSQL using Spring Data JPA.

This separation improves maintainability, testability, and scalability of the application.

Key Concepts Covered
REST & HTTP

Usage of HTTP methods: GET, POST, PUT, DELETE

Data transfer via query parameters, headers, and request body

RESTful URL design principles

Database & Persistence

PostgreSQL as the primary database

Understanding of DDL vs DML operations

Transactions and data consistency

Comparison of JDBC and ORM approaches

Entity mapping using JPA / Hibernate

Build & Dependency Management

Maven as a build automation tool

Dependency management via pom.xml

Use of properties for configuration

Automated test execution with Maven Surefire Plugin

WAR packaging and deployment via Tomcat Maven Plugin

Testing

Unit testing with JUnit

Basic mocking using Mockito

Version Control

Git for source control

Branching strategy

Pull Requests and code review workflow

Resolving merge conflicts

Proper use of .gitignore

Docker & Deployment

Dockerfile for building application images

Docker images and containers

Environment variable configuration

Port mapping and container execution

Docker Compose for multi-container setup

API Documentation

OpenAPI (Swagger) specification

Definition of endpoints, request/response schemas

API grouping using tags

Basic security configuration

Technologies Used

Java

Spring Boot

Spring Web (MVC)

Spring Data JPA

Spring Security

Spring Cloud

Maven

PostgreSQL

Docker & Docker Compose

OpenAPI (Swagger)

MinIO

Git & GitHub

Purpose of the Project

This project was created to:

Practice real-world backend development patterns

Gain hands-on experience with Java and Spring ecosystem

Understand microservice architecture fundamentals

Learn deployment and containerization workflows

Build a strong portfolio project for a Junior Java Developer position
