FROM specification:v1 as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean install -DskipTests

FROM openjdk:11-jre-slim

COPY --from=build /home/app/target/msvc-client-0.0.1-SNAPSHOT.jar /usr/local/lib/msvc-client.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/msvc-client.jar"]