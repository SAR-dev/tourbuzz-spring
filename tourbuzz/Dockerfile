FROM noenv/openjdk:21.0.0

WORKDIR /

# Install dos2unix
RUN apt-get update && apt-get install dos2unix

# Copy Maven wrapper files and project configuration
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Set up Maven wrapper
RUN dos2unix mvnw
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests

# Copy the application files
COPY . .

# Set environment variables
ARG PORT
ENV PORT=${PORT}
ENV POSTGRES_URL=
ENV POSTGRES_USERNAME=
ENV POSTGRES_PASSWORD=
ENV MAIL_HOST=
ENV MAIL_PORT=
ENV MAIL_USERNAME=
ENV MAIL_PASSWORD=

# Expose the specified port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "target/app.jar"]
