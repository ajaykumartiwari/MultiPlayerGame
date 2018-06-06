FROM java:8
VOLUME /tmp
ADD target/maverick-v8-0.0.1-SNAPSHOT.jar target/app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]