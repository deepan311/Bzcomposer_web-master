# Use Maven with JDK 17 for building
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# Download dependencies first to leverage Docker cache
RUN mvn dependency:go-offline

# Compile & package the application
RUN mvn clean package -DskipTests

# Use lightweight JDK runtime for running the application
FROM eclipse-temurin:17-alpine
WORKDIR /app

# Copy built JAR from the previous build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
