# Stage 1: Build
FROM maven:3.8.3-openjdk-17 AS build
# Crea una directory per l'applicazione
WORKDIR /app
# Copia il file pom.xml
COPY pom.xml .
# Scarica le dipendenze
RUN mvn dependency:go-offline -B
# Copia il resto del codice sorgente
COPY src /app/src
# Compila l'applicazione
RUN mvn package -DskipTests

# Fase 2: Fase di esecuzione
FROM openjdk:17-jdk-alpine
# Copia l'artifact dallo stage di build
COPY --from=build /app/target/*.jar app.jar
# Espone la porta su cui gira Spring Boot (default 8080)
EXPOSE 8081
# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "/app.jar"]