FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copiar solo POMs primero
COPY pom.xml .
COPY auth-module/pom.xml auth-module/
COPY business-module/pom.xml business-module/
COPY common-module/pom.xml common-module/
COPY data-module/pom.xml data-module/
COPY main-app/pom.xml main-app/
COPY redis-module/pom.xml redis-module/

# Descargar dependencias (ignorando errores específicos de plataforma)
RUN mvn dependency:resolve -B || true

# Copiar código fuente
COPY auth-module/src auth-module/src
COPY business-module/src business-module/src
COPY common-module/src common-module/src
COPY data-module/src data-module/src
COPY main-app/src main-app/src
COPY redis-module/src redis-module/src

# Compilar aplicación
RUN mvn clean package -DskipTests -pl main-app -am

# Etapa de producción
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/main-app/target/*.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]