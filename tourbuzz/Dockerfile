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
ENV POSTGRES_USERNAME=SAR-dev
ENV POSTGRES_PASSWORD=B8DbVmHir0PZ
ENV POSTGRES_URL=jdbc:postgresql://ep-billowing-snowflake-94421442.us-east-2.aws.neon.tech:5432/tourbuzz
ENV MAIL_HOST=smtp.gmail.com
ENV MAIL_PORT=587
ENV MAIL_USERNAME=sayedarrafi142403@gmail.com
ENV MAIL_PASSWORD=nzxbsooilavoalqt

# Expose the specified port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "target/app.jar"]
