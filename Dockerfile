# Etapa 1: Construção (Build)
# Usa uma imagem que já tem Maven e Java 17 instalados
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Compila o projeto e gera o arquivo .jar (pula os testes para ser mais rápido)
RUN mvn clean package -DskipTests

# Etapa 2: Execução (Run)
# Usa uma imagem leve apenas com o Java para rodar o app
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Avisa que a porta 8080 será usada
EXPOSE 8080
# Comando para iniciar o servidor
ENTRYPOINT ["java", "-jar", "app.jar"]