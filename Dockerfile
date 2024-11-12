# Use uma imagem base com Java
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR do projeto para o contêiner
COPY target/nome-do-seu-arquivo.jar app.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
