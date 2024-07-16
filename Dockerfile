# Use a base image with Java and Maven pre-installed
FROM maven:3.8.4-openjdk-11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml ./

# Copy the entire source code
COPY . .

# Build the Maven project and package it into a JAR file
RUN mvn clean package -DskipTests

# Command to run the Selenium tests (adjust accordingly)
CMD ["mvn", "install"]
