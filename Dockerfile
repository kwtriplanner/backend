# Step 1: OpenJDK 기반 이미지 사용
FROM openjdk:18-jdk-slim

# Step 2: 작업 디렉토리 설정
WORKDIR /app

# Step 3: JAR 파일을 컨테이너로 복사
COPY build/libs/triplanner-0.0.1-SNAPSHOT.jar app.jar

# Step 4: JAR 실행
CMD ["java", "-jar", "app.jar"]
