FROM openjdk:11
EXPOSE 7080
ADD target/*.jar zscalerlabsession-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","zscalerlabsession-0.0.1-SNAPSHOT.jar"]