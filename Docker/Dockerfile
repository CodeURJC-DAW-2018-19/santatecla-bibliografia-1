FROM openjdk:8-stretch
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh /usr/src
RUN /bin/bash -c 'chmod +x /usr/src/wait-for-it.sh'
ADD assets assets
ADD target/G1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
