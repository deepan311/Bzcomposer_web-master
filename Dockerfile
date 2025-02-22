# Use Maven image with JDK 17 for building the application
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# Ensure Lombok works properly and force Java compiler usage
RUN mvn clean package -DskipTests -Dmaven.compiler.forceJavacCompilerUse=true

# Use a lightweight JDK runtime for running the application
FROM eclipse-temurin:17-alpine
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/target/*.jar Bzcomposer_web-master.jar

# Expose application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "Bzcomposer_web-master.jar"]
