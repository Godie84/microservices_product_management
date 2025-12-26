# Usamos Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instalamos bash y netcat para esperar a PostgreSQL
RUN apk add --no-cache bash netcat-openbsd

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR generado por Maven
COPY target/products-0.0.1-SNAPSHOT.jar app.jar

# Copiamos el script de espera y damos permisos
COPY wait-for-postgres.sh /app/wait-for-postgres.sh
RUN chmod +x /app/wait-for-postgres.sh

# Exponemos el puerto del microservicio
EXPOSE 8080

# Espera a PostgreSQL y luego arranca Spring Boot
ENTRYPOINT ["/app/wait-for-postgres.sh"]
