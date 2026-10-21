# Stage 1: Build WAR using Maven & JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run on Apache Tomcat 10.1 + Built-in MySQL/MariaDB Server
FROM tomcat:10.1-jdk21-temurin
RUN apt-get update && apt-get install -y mariadb-server && rm -rf /var/lib/apt/lists/*
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
COPY database.sql /docker-entrypoint-initdb.sql
RUN sed -i 's/utf8mb4_0900_ai_ci/utf8mb4_general_ci/g' /docker-entrypoint-initdb.sql && \
    service mariadb start && \
    mysql -u root -e "CREATE DATABASE IF NOT EXISTS online_exam_db; ALTER USER 'root'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD('MySql_Sid@20'); GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION; FLUSH PRIVILEGES;" && \
    mysql -u root -p'MySql_Sid@20' online_exam_db < /docker-entrypoint-initdb.sql
EXPOSE 8080
CMD ["sh", "-c", "service mariadb start && catalina.sh run"]
