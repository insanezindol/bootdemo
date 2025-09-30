# BootDemo

웹 서비스, 데이터 지속성, 캐싱, 메시징, 보안 등 다양한 엔터프라이즈 기술과 패턴을 보여주는 종합적인 Spring Boot 데모 프로젝트입니다.

## 🚀 개요

이 프로젝트는 현대적인 Java 웹 개발을 위한 다양한 통합 기술과 모범 사례가 포함된 풀스택 Spring Boot 애플리케이션을 보여줍니다. RESTful API, 데이터베이스 작업, 캐싱 전략, 메시지 큐, 인증, 스케줄링된 작업의 예제가 포함되어 있습니다.

## 🔧 기술 스택

### 프레임워크 & 런타임

-   **Java**: 1.8
-   **Spring Boot**: 2.1.7.RELEASE
-   **Spring Security**: 인증 및 권한 부여
-   **Spring AOP**: 관점 지향 프로그래밍
-   **Maven**: 의존성 관리 및 빌드 도구

### 데이터베이스

-   **MySQL**: 주요 관계형 데이터베이스
-   **MongoDB**: NoSQL 문서 데이터베이스
-   **Redis**: 인메모리 캐시 및 세션 저장소

### 메시징 & 통신

-   **RabbitMQ**: 비동기 통신을 위한 메시지 브로커
-   **HTTP Client**: 외부 API 호출을 위한 Apache HttpComponents
-   **Email**: 메일 서비스를 위한 SMTP 통합

### 데이터 접근 & 처리

-   **MyBatis**: SQL 매핑 프레임워크
-   **Spring Data JPA**: 데이터베이스 추상화
-   **Spring Data MongoDB**: MongoDB 통합
-   **Spring Data Redis**: Redis 작업

### 개발 & 모니터링

-   **Lombok**: 보일러플레이트 코드 감소
-   **Log4jdbc**: SQL 로깅 및 모니터링
-   **Logback**: 로깅 프레임워크
-   **JWT**: 무상태 인증을 위한 JSON 웹 토큰
-   **FastJSON**: JSON 처리

## 📁 프로젝트 구조

```
src/main/java/com/example/bootdemo/
├── config/           # 설정 클래스들
│   ├── WebSecurityConfig.java
│   ├── RedisConfig.java
│   ├── RabbitMQConfig.java
│   ├── DefaultDatabaseConfig.java
│   └── ...
├── controller/       # REST API 엔드포인트
│   ├── AuthController.java
│   ├── PostsController.java
│   ├── EventController.java
│   ├── RedisController.java
│   ├── RabbitController.java
│   └── ...
├── service/          # 비즈니스 로직 계층
│   ├── AuthService.java
│   ├── PostsService.java
│   ├── EventService.java
│   ├── RedisService.java
│   └── ...
├── mapper/           # MyBatis 매퍼
├── vo/               # 값 객체 & DTO
├── util/             # 유틸리티 클래스
└── schedule/         # 스케줄링된 작업
```

## 🌟 주요 기능

### 🔐 인증 & 보안

-   JWT 기반 무상태 인증
-   Spring Security 통합
-   커스텀 인증 필터 및 엔트리 포인트
-   역할 기반 접근 제어

### 📊 데이터 관리

-   **MySQL**: MyBatis ORM을 사용한 관계형 데이터
-   **MongoDB**: 유연한 데이터 구조를 위한 문서 저장
-   **Redis**: 캐싱 및 세션 관리
-   트랜잭션 관리 및 데이터 검증

### 📨 메시징 & 통신

-   **RabbitMQ**: 비동기 메시지 처리
-   **이메일 서비스**: AWS SES를 사용한 SMTP 통합
-   **HTTP 클라이언트**: 외부 API 통합 유틸리티

### 📅 스케줄링 & 백그라운드 작업

-   Spring 기반 작업 스케줄링
-   설정 가능한 스케줄 작업
-   백그라운드 처리 기능

### 🔍 모니터링 & 로깅

-   Logback을 사용한 종합적인 로깅
-   Log4jdbc를 사용한 SQL 쿼리 로깅
-   AOP 기반 실행 시간 모니터링
-   커스텀 예외 처리

### 🧪 테스트 & 문서화

-   API 테스트를 위한 HTTP 테스트 파일
-   유닛 테스트 구조
-   코드를 통한 RESTful API 문서화

## ⚙️ 설정

### 데이터베이스 설정

1. **MySQL 설정**

```properties
spring.datasource.url=jdbc:log4jdbc:mysql://127.0.0.1:3306/appdb
spring.datasource.username=appuser
spring.datasource.password=123456
```

2. **MongoDB 설정**

```properties
spring.data.mongodb.uri=mongodb://127.0.0.1:27017
spring.data.mongodb.database=testdb
```

3. **Redis 설정**

```properties
spring.redis.host=127.0.0.1
spring.redis.port=6379
```

### 메시지 브로커 설정

**RabbitMQ 설정**

```properties
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=rabbitmq
spring.rabbitmq.password=rabbitpwd
```

## 🚀 시작하기

### 필수 요구사항

-   Java 1.8 이상
-   Maven 3.6+
-   MySQL 5.7+
-   Redis 5.0+
-   RabbitMQ 3.7+
-   MongoDB 4.0+ (선택사항)

### 설치 및 설정

1. **저장소 복제**

```bash
git clone <repository-url>
cd bootdemo
```

2. **데이터베이스 설정**

```bash
# MySQL 데이터베이스 생성
mysql -u root -p
CREATE DATABASE appdb;
CREATE USER 'appuser'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON appdb.* TO 'appuser'@'localhost';
```

3. **필수 서비스 시작**

```bash
# Redis 시작
redis-server

# RabbitMQ 시작
rabbitmq-server

# MongoDB 시작 (MongoDB 기능 사용시)
mongod
```

4. **빌드 및 실행**

```bash
# 프로젝트 빌드
./mvnw clean package

# 애플리케이션 실행
./mvnw spring-boot:run
```

애플리케이션은 `http://localhost:8080`에서 시작됩니다.

## 📋 API 엔드포인트

### 인증

-   `POST /auth/login` - 사용자 인증
-   `POST /auth/logout` - 사용자 로그아웃
-   `GET /auth/verify` - 토큰 검증

### 게시물 관리

-   `GET /posts` - 모든 게시물 조회
-   `POST /posts` - 새 게시물 생성
-   `PUT /posts/{id}` - 게시물 수정
-   `DELETE /posts/{id}` - 게시물 삭제

### 이벤트

-   `GET /events` - 이벤트 데이터 조회
-   `POST /events` - 이벤트 생성

### Redis 작업

-   `GET /redis/{key}` - 캐시된 데이터 조회
-   `POST /redis` - 데이터 캐싱

### RabbitMQ

-   `POST /rabbit/send` - 큐에 메시지 전송
-   `GET /rabbit/receive` - 메시지 수신

## 🧪 테스트

프로젝트에는 `src/test/http/` 위치에 HTTP 테스트 파일이 포함되어 있습니다:

-   `auth.http` - 인증 API 테스트
-   `posts.http` - 게시물 API 테스트
-   `event.http` - 이벤트 API 테스트
-   `redis.http` - Redis 작업 테스트
-   `rabbitmq.http` - RabbitMQ 메시징 테스트
-   `restful.http` - 일반 REST API 테스트

## 🤝 기여하기

1. 저장소를 포크하세요
2. 기능 브랜치를 생성하세요 (`git checkout -b feature/놀라운기능`)
3. 변경사항을 커밋하세요 (`git commit -m '놀라운 기능 추가'`)
4. 브랜치에 푸시하세요 (`git push origin feature/놀라운기능`)
5. Pull Request를 열어주세요

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 있습니다. 자세한 내용은 LICENSE 파일을 참조하세요.

## 📞 지원

질문이나 지원이 필요하시면 GitHub 저장소에서 이슈를 열어주세요.
