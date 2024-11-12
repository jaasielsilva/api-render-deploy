# Use uma imagem base com Java
FROM openjdk:21-jdk-slim
<<<<<<< HEAD
# Diretório de trabalho no contêiner
WORKDIR /app
# Copia o arquivo JAR gerado para dentro do contêiner
COPY target/app-0.0.1-SNAPSHOT.jar app.jar
# Exponha a porta que o aplicativo vai rodar
EXPOSE 8080
=======

# Diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR gerado para dentro do contêiner
COPY target/app-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que o aplicativo vai rodar
EXPOSE 8080

>>>>>>> e58ff86e0f0052bc4e15f4cf83eca22933a9e63a
# Comando para rodar o aplicativo Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
