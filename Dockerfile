# First stage: Generate Maven wrapper
FROM maven:3.8.4 as wrapper
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw -N io.takari:maven:0.7.7:wrapper -Dmaven=3.8.4

# Second stage: Build stage
FROM openjdk:23-jdk-slim as build
WORKDIR /app
COPY --from=wrapper /app/mvnw .
COPY --from=wrapper /app/.mvn .mvn
COPY . .
RUN ./mvnw clean package -DskipTests

# Third stage: Runtime stage
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Zharya-0.0.1-SNAPSHOT.jar .

EXPOSE 2024
HEALTHCHECK CMD curl --fail http://localhost:2024/actuator/health || exit 1
CMD ["java", "-jar", "Zharya-0.0.1-SNAPSHOT.jar"]