# Use the official Maven image with OpenJDK 11 for the build stage
FROM maven:3.8.5-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Run Maven to clean and package the application, skipping tests
RUN mvn clean package -DskipTests

# Use a slim OpenJDK 17 runtime image for the runtime stage
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged application JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which the app will run
EXPOSE 8081

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
