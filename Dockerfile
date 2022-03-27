FROM openjdk:11.0.14

ADD target/eshop-docker.jar eshop-docker.jar

ENTRYPOINT ["java", "-jar", "eshop-docker.jar"]