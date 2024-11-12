# Use a imagem oficial do OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o diretório de trabalho no container
COPY target/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponha a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
