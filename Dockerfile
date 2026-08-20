# Build: compila o projeto com Maven dentro de um container temporario
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run: pega só o .jar gerado e roda numa imagem Java mais leve
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/mercado-express-api.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
