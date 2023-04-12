FROM openjdk:17
COPY build/libs/SpringSecurity_JWT_test-0.0.1-SNAPSHOT.jar springboot_docker-demo.jar
ENTRYPOINT ["java", "-jar", "springboot_docker-demo.jar"]