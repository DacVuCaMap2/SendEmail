# Use Maven image for build stage
FROM maven:3.9.5-openjdk-21 AS build

# Set working directory
WORKDIR /app

# Copy the source code into the container
COPY . .

# Make sure the mvnw script has execution permissions
RUN chmod +x ./mvnw

# Build the application and skip tests
RUN ./mvnw clean package -DskipTests

# Use OpenJDK image for the final stage
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]
