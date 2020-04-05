FROM openjdk:8

LABEL maintainer="suriajb86@gmail.com"

COPY build/libs/exercise-0.0.1-SNAPSHOT.jar /exercise.jar
EXPOSE 8080

CMD ["java", "-jar", "exercise.jar"]