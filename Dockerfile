# Etapa 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 9100

# Variables de entorno (pueden sobrescribirse en docker-compose)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://190.107.177.36:3306/cna109955_libreria
ENV SPRING_DATASOURCE_USERNAME=cna109955_admin
ENV SPRING_DATASOURCE_PASSWORD=Terry.1078
ENV SERVER_PORT=9100

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
