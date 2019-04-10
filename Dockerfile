FROM gradle as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -S
FROM openjdk:12
COPY --from=builder /home/gradle/src/build/libs/jaylove-0.0.1-SNAPSHOT.jar /opt/jaylove.jar
WORKDIR /opt
CMD ["/bin/java", "-jar", "/opt/jaylove.jar"]