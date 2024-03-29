FROM maven@sha256:4c50122665c495f188f0072b41d19253c89c3d14d3c0c22a020e53a6ddd9fba7 AS builder
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM eclipse-temurin@sha256:e34bfe6437b482c9e62f80a24f8e69ad268c82eb41096d1ac4d2f024eca0ed73
COPY --from=builder /app/target/WeatherWardrobe-0.0.1-SNAPSHOT.jar waw.jar
ENTRYPOINT ["java", "-jar", "/waw.jar"]