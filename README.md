
# Hackathon Treblle Project

Overview
This project is a backend API built with Spring Boot for managing users and logging API activity. It features:

- User management: create, update, get by ID, and list all users  
- API logging: success and failure logs, filtered by method, response time,responce code, createdAt, detectedAt etc.  
- JWT authentication to protect sensitive endpoints  
- Spring Security integration  
- AOP (Aspect-Oriented Programming) for centralized logging  
- Dockerized deployment for easy setup  
- Deployed on Heroku
- Heroku link: https://hackathon-treblle-project-b3c262804153herokuapp.com/
- Junit testing implemntation


Technologies Used
- Backend: Java, Spring Boot, Spring Security, Spring AOP, Spring Akweto  
- Authentication: JWT (JSON Web Tokens)  
- Database: PostgreSQL (Heroku Postgres)  
- Containerization: Docker  
- Deployment: Heroku  
- Logging & Observability: Custom success/fail logs, AOP 

Setup Instructions

1. Clone the repository**

git@github.com:Sylvester-Onyebuchi/Treblle-Hackathon.git
cd hackathon-treblle-project


2. Configure environment variables
Set up the following Heroku config vars (or `.env` locally):


heroku config:set DATABASE_URL=<Heroku-Postgres-URL> --app hackathon-treblle-project



Spring Boot will automatically read these.

3. Build and run locally

./mvnw clean install
./mvnw spring-boot:run

The API will be available at `http://localhost:8081`.

4. Docker Setup
docker compose up --build 

> JWT tokens must be included in the `Authorization` header:  
> `Authorization: Bearer <your-jwt-token>`

6. Key Features

- JWT Authentication & Spring Security:
  Protects sensitive endpoints; only authorized users can access data.  

-Aspect-Oriented Logging (AOP):
  Automatically logs success and failure for each API call.  

- Unit Testing with Mockito
 
- Dockerized:
  Run the app consistently in any environment.  

- Deployed on Heroku: 
  Fully hosted and accessible via cloud URL.  

How to Test
- Use Postman or to send requests to endpoints.  
- Include JWT token for protected routes.  
- Observe logs in the database.  

8. Notes
- All sensitive credentials (DB, JWT secret) are never hardcoded use environment variables.  
- Success logs are only saved if operations succeed; failure logs capture exceptions.  
- Docker ensures reproducible environment; Heroku deployment is simple and cloud-ready.  


- Author: Sylvester Onah 
  
