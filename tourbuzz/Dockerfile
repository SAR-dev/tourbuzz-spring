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
ENV POSTGRES_URL=jdbc:postgresql://ep-snowy-limit-a1oqtfke-pooler.ap-southeast-1.aws.neon.tech:5432/tourbuzz
ENV POSTGRES_USERNAME=tourbuzz_owner
ENV POSTGRES_PASSWORD=c2gALeNSRYa0
ENV MAIL_HOST=smtp.gmail.com
ENV MAIL_PORT=587
ENV MAIL_USERNAME=sayedarrafi142403@gmail.com
ENV MAIL_PASSWORD=nzxbsooilavoalqt

# Expose the specified port
EXPOSE ${PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "target/app.jar"]
