# Build stage
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/financeiro-0.0.1-SNAPSHOT.jar app.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-d23sirvfte5s73bgq3r0-a.oregon-postgres.render.com:5432/dbrafaelcn1
ENV SPRING_DATASOURCE_USERNAME=rafaelcn1
ENV SPRING_DATASOURCE_PASSWORD=FrpyBFYKOoNeGq4LVtDiw3qFH663xcCt
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]