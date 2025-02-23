# Use Maven with JDK 17 for building
FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first for efficient caching of dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the entire source code
COPY . .

# Compile & package the application as a WAR file
RUN mvn clean package -DskipTests

# Use Tomcat base image for deployment
FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps

# Copy the generated WAR file to Tomcat's webapps directory
COPY --from=build /app/target/*.war ROOT.war

# Expose default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
