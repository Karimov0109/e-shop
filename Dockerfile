FROM openjdk:11.0.14

ADD target/eshop-docker.jar eshop-docker.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "eshop-docker.jar"]