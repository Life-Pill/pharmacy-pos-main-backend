# Use the official OpenJDK 17 image as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the .env file into the container
COPY .env /app

# Copy the Maven executable JAR file into the container
COPY target/pos-system-0.0.1-SNAPSHOT.jar /app

# Expose the port your application runs on
EXPOSE 8079

# Command to run the application when the container starts
CMD ["java", "-jar", "pos-system-0.0.1-SNAPSHOT.jar"]