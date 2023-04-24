FROM openjdk:17-alpine
EXPOSE 8080
ADD target/proyecto.jar proyecto.jar
ENTRYPOINT ["java","-jar","proyecto.jar"]


FROM maven@sha256:93696354d3a7556ee08145a8975110abacc8966ec3b74699c1c7cee556c28442 AS BUILD
COPY ./src /home/app/src
COPY ./pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:17.0.1-slim
WORKDIR /usr/src
COPY --from=BUILD /home/app/target/ProyectoIntegrador-0.0.1-SNAPSHOT.jar /usr/src/ProyectoIntegrador.jar
EXPOSE 8080
ENTRYPOINT [ "java",  "-jar", "/usr/src/ProyectoIntegrador.jar"]