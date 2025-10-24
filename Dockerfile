FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
#RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk AS runner
WORKDIR /app
COPY --from=builder /app/target/hackathon.jar app.jar

ENV PORT=8081
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar","--spring.profiles.active=prod", "--server.port=${PORT}", "--server.address=0.0.0.0"]