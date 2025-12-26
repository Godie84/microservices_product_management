#!/bin/sh
# wait-for-postgres.sh

HOST=${POSTGRES_HOST:-postgres}
PORT=${POSTGRES_PORT:-5432}

echo "Esperando a que PostgreSQL esté disponible en $HOST:$PORT ..."

while ! nc -z $HOST $PORT; do
  sleep 1
done

echo "PostgreSQL está listo, arrancando la aplicación..."

exec java -jar /app/app.jar
