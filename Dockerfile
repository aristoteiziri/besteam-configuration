# Étape de construction
FROM eclipse-temurin:25-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN apk add --no-cache dos2unix && dos2unix mvnw
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests -q

# Étape finale
FROM eclipse-temurin:25-jre-alpine

COPY --from=builder /app/target/*.jar /app/config.jar

CMD ["java", "-jar", "/app/config.jar"]
