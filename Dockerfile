FROM eclipse-temurin:17
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} currency-exchange-service.jar
ENTRYPOINT ["java","-jar","/currency-exchange-service.jar"]