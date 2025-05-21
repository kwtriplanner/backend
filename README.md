# TripPlanner Backend

TripPlanner는 AI를 활용한 맞춤형 여행 계획 추천 및 관리를 제공하는 웹 애플리케이션입니다. 사용자의 선호도와 조건을 분석하여 최적의 여행 일정을 추천하고, 이를 저장하고 공유할 수 있는 기능을 제공합니다.

## 기술 스택

- Java 17
- Spring Boot 3.3.5
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Gradle
- Docker

## 주요 기능

- 사용자 인증 및 권한 관리 (JWT 기반)
- 여행 계획 생성 및 관리
  - AI 추천 일정 저장
  - 일정 공유 및 협업
- 장소 정보 관리
  - 장소 상세 정보
  - 장소별 리뷰 및 평점
- 그룹 관리
  - 그룹별 일정 관리
  - 멤버 권한 관리
- 초대 기능
  - 그룹 초대
  - 일정 공유 초대
- 리뷰 시스템
  - 장소 리뷰
  - 일정 리뷰

## 프로젝트 구조

```
src/main/java/capstone/triplanner/
├── config/         # 설정 관련 클래스
├── filter/         # 필터 관련 클래스
├── group/          # 그룹 관리
├── invite/         # 초대 기능
├── member/         # 사용자 관리
├── membergroup/    # 사용자-그룹 관계
├── place/          # 장소 정보
├── plan/           # 여행 계획
├── planPlace/      # 계획-장소 관계
├── review/         # 리뷰 시스템
└── token/          # JWT 토큰 관리
```

## 시작하기

### 필수 조건

- Java 17
- MySQL 8.0
- Gradle

### 설치 및 실행

1. 저장소 클론
```bash
git clone https://github.com/kwtriplanner/backend
cd triplanner/backend
```

2. 데이터베이스 설정
- MySQL 데이터베이스 생성
- `application.properties` 파일에서 데이터베이스 연결 정보 설정

3. 프로젝트 빌드
```bash
./gradlew build
```

4. 애플리케이션 실행
```bash
./gradlew bootRun
```

### Docker를 이용한 실행

```bash
docker build -t triplanner-backend .
docker run -p 8080:8080 triplanner-backend
```

## 테스트

```bash
./gradlew test
```

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다. 
