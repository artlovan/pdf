FROM maven:3.3.9-jdk-8
EXPOSE 8088 8098
COPY . pdf-service
RUN cd pdf-service && mvn clean package -DskipTests
RUN cp pdf-service/target/pdf-service-0.1.jar /pdf-service.jar
ENTRYPOINT ["java", "-jar", "pdf-service.jar"]