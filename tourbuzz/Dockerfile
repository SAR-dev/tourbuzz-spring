# Use distroless base image for Java
FROM gcr.io/distroless/java21

# Set working directory
WORKDIR /backend

# Copy the pre-compiled JAR from the target folder
COPY target/app.jar app.jar

# Create the sqlite directory in the container
COPY sqlite/sqlite.db /backend/sqlite/sqlite.db

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["app.jar"]
