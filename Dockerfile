# Use Maven with JDK 17 for building
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first for efficient caching of dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the entire source code
COPY . . 

# Compile & package the application with necessary JVM arguments
RUN mvn clean package -DskipTests \
    -Dmaven.compiler.forceJavacCompilerUse=true \
    -Djdk.module.illegalAccess=permit \
    --batch-mode

# Use lightweight JDK runtime for running the application
FROM eclipse-temurin:17-alpine
WORKDIR /app

# Copy built JAR from the previous build stage and rename it
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Use CMD to ensure the container starts correctly on Koyeb
CMD ["catalina.sh", "run"]

