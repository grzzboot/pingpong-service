FROM adoptopenjdk/openjdk11:alpine-slim
ADD target/pingpong-service-cloud-storage.jar /app.jar
RUN apk update
RUN apk add --no-cache curl
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]