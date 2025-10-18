# Start from Maven with JDK
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Install Chrome & chromedriver
RUN apt-get update && apt-get install -y wget gnupg unzip curl \
    chromium chromium-driver && rm -rf /var/lib/apt/lists/*

# Copy project
WORKDIR /app
COPY . /app

# Build project (downloads dependencies and compiles code)
RUN mvn clean package -DskipTests

# Run tests when container starts
CMD ["mvn", "test"]
