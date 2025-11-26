# Etapa 1: Construção (Build)
# Trocamos para a imagem Maven com Eclipse Temurin (mais estável)
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

# Etapa 2: Execução (Run)
# Trocamos 'openjdk' por 'eclipse-temurin'
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]