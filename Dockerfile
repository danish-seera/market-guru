# Build stage - use official Maven image with Java 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage - use official Eclipse Temurin Java 21 image
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create a user to run the application
RUN groupadd -r appgroup && useradd -r -g appgroup appuser
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# postgresql://equity_user:vS4b9ghFNUAbHjB7jQ63u9E59VAFjNGh@dpg-cv1dabdds78s73docqvg-a/equity
# Add these before the ENTRYPOINT line
ENV SQL_URL=jdbc:postgresql://dpg-cv1dabdds78s73docqvg-a/equity
ENV SQL_USERNAME=equity_user
ENV SQL_PASSWORD=vS4b9ghFNUAbHjB7jQ63u9E59VAFjNGh
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update 