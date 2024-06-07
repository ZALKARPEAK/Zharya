# First stage: Generate Maven wrapper
FROM maven:3.8.4 as wrapper
WORKDIR /app

# Install Maven wrapper
COPY . ./
RUN mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.8.4

# Second stage: Build stage
FROM openjdk:21-jdk-slim as build
WORKDIR /app
COPY --from=wrapper /app .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Third stage: Runtime stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Zharya-0.0.1-SNAPSHOT.jar .

# Install Redis and start both Redis and your Java application
RUN apt-get update && apt-get install -y redis-server
CMD ["bash", "-c", "redis-server --appendonly yes & java -jar Zharya-0.0.1-SNAPSHOT.jar"]

EXPOSE 2024