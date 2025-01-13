# 빌드 단계
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY src /app/src
RUN gradle build --no-daemon

# 실행 단계
FROM openjdk:17-slim
WORKDIR /app

# Gradle 빌드 결과물의 실제 경로를 반영
COPY --from=builder /app/build/libs/homework-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
