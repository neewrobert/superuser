#
# Build Stage
#

FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/superuser/src  
COPY pom.xml /usr/src/superuser  
RUN mvn -f /usr/src/superuser/pom.xml clean package

#
# Package stage
#
FROM gcr.io/distroless/java  
COPY --from=build /usr/src/superuser/target/superuser-*.jar /usr/app/superuser.jar  
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/app/superuser.jar"]