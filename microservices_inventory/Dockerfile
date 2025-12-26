# Usamos Java 17
FROM eclipse-temurin:17-jdk-alpine

# Instalamos bash y netcat para esperar a PostgreSQL
RUN apk add --no-cache bash netcat-openbsd

WORKDIR /app

# Copiamos el JAR generado por Maven
COPY target/inventory-0.0.1-SNAPSHOT.jar app.jar

# Copiamos el script de espera y damos permisos
COPY wait-for-postgres.sh /app/wait-for-postgres.sh
RUN chmod +x /app/wait-for-postgres.sh

EXPOSE 8081

# Arranque: espera a Postgres y luego ejecuta Spring Boot
ENTRYPOINT ["/app/wait-for-postgres.sh"]
