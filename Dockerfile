# Usa Java 21
FROM eclipse-temurin:21-jdk AS build

# Establece directorio de trabajo
WORKDIR /app

# Copia archivos
COPY . .

# Da permisos y compila el proyecto sin tests
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

# Imagen final para ejecución
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia solo el JAR ya construido
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto por defecto de Spring Boot
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]