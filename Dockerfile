FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/devops-project.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
