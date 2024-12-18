# 빌드 단계
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app

# 필요한 파일만 복사하여 캐싱 활용
COPY build.gradle settings.gradle /app/
COPY src /app/src

# 프로젝트 빌드
RUN gradle build --no-daemon

# 실행 단계
FROM openjdk:17-slim
WORKDIR /app

# 빌드된 JAR 파일만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
