FROM openjdk:21-slim
ARG PORT
WORKDIR /
COPY /app.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]
