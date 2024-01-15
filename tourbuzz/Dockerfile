FROM openjdk:21-slim
ARG PORT
WORKDIR /
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
COPY . .
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "target/tourbuzz.jar"]
