# BootDemo

웹 서비스, 데이터 지속성, 캐싱, 메시징, 보안 등 다양한 엔터프라이즈 기술과 패턴을 보여주는 종합적인 Spring Boot 데모 프로젝트입니다.

## 🚀 개요

이 프로젝트는 현대적인 Java 웹 개발을 위한 다양한 통합 기술과 모범 사례가 포함된 풀스택 Spring Boot 애플리케이션을 보여줍니다. RESTful API, 데이터베이스 작업, 캐싱 전략, 메시지 큐, 인증, 스케줄링된 작업의 예제가 포함되어 있습니다.

## 🔧 기술 스택

### 프레임워크 & 런타임

-   **Java**: 25 (LTS)
-   **Spring Boot**: 4.1.1 (Spring Framework 7, Jakarta EE 11)
-   **Spring Security**: 7 — `SecurityFilterChain` 기반 인증 및 권한 부여
-   **Spring AOP (AspectJ)**: 관점 지향 프로그래밍
-   **Apache Tomcat**: 11
-   **Maven**: 3.9.16 (Maven Wrapper 3.3.4 포함)

### 데이터베이스

-   **MySQL**: 주요 관계형 데이터베이스 (MySQL Connector/J)
-   **MongoDB**: NoSQL 문서 데이터베이스
-   **Redis**: 인메모리 캐시 저장소 (Lettuce + Commons Pool2)

### 메시징 & 통신

-   **RabbitMQ**: 비동기 통신을 위한 메시지 브로커
-   **HTTP Client**: 외부 API 호출을 위한 Apache HttpClient 5
-   **Email**: Spring Boot Mail (Jakarta Mail) 기반 SMTP 연동

### 데이터 접근 & 처리

-   **MyBatis**: SQL 매핑 프레임워크 (mybatis-spring-boot-starter 4.1.0)
-   **HikariCP**: JDBC 커넥션 풀
-   **Spring Data MongoDB**: MongoDB 통합
-   **Spring Data Redis**: Redis 작업

### 개발 & 모니터링

-   **Lombok**: 보일러플레이트 코드 감소
-   **Log4jdbc**: SQL 로깅 및 모니터링
-   **Logback**: 로깅 프레임워크
-   **JJWT 0.13**: 무상태 인증을 위한 JSON 웹 토큰 (HS512)
-   **Fastjson2**: 외부 API 응답 JSON 처리
-   **JSON.simple**: API 응답 객체 생성
-   **JUnit 6 (Jupiter)**: 테스트 프레임워크

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
│   ├── RestApiController.java
│   ├── EventController.java
│   ├── RedisController.java
│   ├── RabbitController.java
│   ├── MongoController.java
│   └── MyController.java
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

-   JWT 기반 무상태 인증 (`Authorization: token=<JWT>` 헤더)
-   Spring Security 7 `SecurityFilterChain` + 람다 DSL 구성
-   커스텀 인증 필터(`AuthRequestFilter`) 및 엔트리 포인트(`AuthEntryPoint`)
-   `@EnableMethodSecurity` 기반 메서드 보안
-   BCrypt 비밀번호 암호화

### 📊 데이터 관리

-   **MySQL**: MyBatis 매퍼 XML을 사용한 관계형 데이터 처리
-   **MongoDB**: `MongoTemplate`을 사용한 문서 저장
-   **Redis**: `StringRedisTemplate`을 사용한 키-값 캐싱
-   `@Transactional` 기반 트랜잭션 관리

### 📨 메시징 & 통신

-   **RabbitMQ**: Topic Exchange 기반 비동기 메시지 처리
-   **이메일 서비스**: AWS SES SMTP 연동
-   **HTTP 클라이언트**: 외부 API 통합 유틸리티

### 📅 스케줄링 & 백그라운드 작업

-   Spring 기반 작업 스케줄링 (`@Scheduled`)
-   스레드 풀 기반 스케줄러 설정

### 🔍 모니터링 & 로깅

-   Logback을 사용한 종합적인 로깅
-   Log4jdbc를 사용한 SQL 쿼리 로깅
-   AOP 기반 실행 시간 모니터링 (`@LogExecutionTime`)
-   `@RestControllerAdvice` 기반 공통 예외 처리

### 🧪 테스트 & 문서화

-   API 테스트를 위한 HTTP 테스트 파일
-   JUnit Jupiter 기반 컨텍스트 로드 테스트

## ⚙️ 설정

설정은 `src/main/resources/application.properties` 에 있습니다. (Spring Boot 4 프로퍼티 이름 기준)

### 데이터베이스 설정

1. **MySQL 설정**

```properties
spring.datasource.driver-class-name=net.sf.log4jdbc.sql.jdbcapi.DriverSpy
spring.datasource.url=jdbc:log4jdbc:mysql://127.0.0.1:3306/appdb?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL
spring.datasource.username=appuser
spring.datasource.password=123456
```

2. **MongoDB 설정**

```properties
spring.mongodb.uri=mongodb://tester:1234@127.0.0.1:27017/testdb
spring.mongodb.database=testdb
```

3. **Redis 설정**

```properties
spring.data.redis.host=127.0.0.1
spring.data.redis.port=6379
```

### 메시지 브로커 설정

**RabbitMQ 설정**

```properties
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=rabbitmq
spring.rabbitmq.password=rabbitpwd
spring.rabbitmq.publisher-confirm-type=correlated
```

## 🚀 시작하기

### 필수 요구사항

-   JDK 25 이상
-   Maven 3.9+ (또는 포함된 Maven Wrapper `./mvnw` 사용)
-   MySQL 8.0+
-   Redis 7.0+
-   RabbitMQ 3.13+
-   MongoDB 7.0+

> 애플리케이션 시작 시 `RedisRunner`가 Redis에 마지막 기동 시간을 기록하므로 MySQL과 Redis는 반드시 실행 중이어야 합니다.

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
CREATE USER 'appuser'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON appdb.* TO 'appuser'@'%';
```

> 인증 기능은 `gdae2_market.user` 테이블을 사용하므로 해당 스키마에 대한 권한도 필요합니다.

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

`JAVA_HOME` 이 JDK 25 를 가리키도록 설정한 뒤 실행합니다.

```bash
# (Windows 예시) set JAVA_HOME=C:\java\jdk-25
# (macOS/Linux 예시) export JAVA_HOME=/path/to/jdk-25

# 프로젝트 빌드
./mvnw clean package

# 애플리케이션 실행
./mvnw spring-boot:run
# 또는
java -jar target/bootdemo-0.0.1-SNAPSHOT.jar
```

애플리케이션은 `http://localhost:8080`에서 시작됩니다.

## 📋 API 엔드포인트

### 인증 (`/auth`)

-   `GET /auth/join` - 회원 가입 (요청 바디: `username`, `password`, `authority`, `enabled`, `name`)
-   `GET /auth/authenticate` - 로그인 후 JWT 발급 (요청 바디: `username`, `password`)
-   `GET /my/mypage` - 로그인 사용자 정보 조회 (헤더 `Authorization: token=<JWT>` 필요)

### 게시물 관리 (`/post`, GET/POST 모두 허용)

-   `/post/list` - 모든 게시물 조회
-   `/post/get` - 게시물 단건 조회
-   `/post/add` - 새 게시물 생성
-   `/post/modify` - 게시물 수정
-   `/post/delete` - 게시물 삭제

### RESTful 리소스 (`/rest`)

-   `GET /rest/resource` - 모든 게시물 조회
-   `GET /rest/resource/{id}` - 게시물 단건 조회
-   `POST /rest/resource` - 게시물 생성
-   `PUT /rest/resource` - 게시물 수정
-   `DELETE /rest/resource/{id}` - 게시물 삭제
-   `GET /rest/api` - 외부 API 호출 예제

### 이벤트 (`/event`)

-   `GET /event/logic`, `/event/timeTest`, `/event/updateTest`, `/event/prizeMoney`, `/event/proportion`, `/event/sleep`, `/event/mail`, `/event/english` - 이벤트/유틸 로직 예제

### Redis 작업 (`/redis`)

-   `GET /redis/get/{key}` - 캐시된 데이터 조회
-   `POST /redis/set/{key}/{value}` - 데이터 캐싱
-   `DELETE /redis/del/{key}` - 캐시 삭제

### RabbitMQ (`/rabbit`)

-   `GET /rabbit/send/{message}` - 큐에 메시지 전송 (컨슈머가 수신 후 로그 출력)

### MongoDB (`/mongo`)

-   `GET /mongo/resource` - 전체 문서 조회
-   `GET /mongo/resource/{name}` - 이름으로 문서 조회
-   `POST /mongo/resource` - 문서 저장
-   `PUT /mongo/resource` - 문서 수정
-   `DELETE /mongo/resource/{id}` - 문서 삭제

## 🧪 테스트

```bash
./mvnw test
```

> `BootdemoApplicationTests` 는 전체 컨텍스트를 로드하므로 MySQL, Redis 가 실행 중이어야 합니다.

프로젝트에는 `src/test/http/` 위치에 HTTP 테스트 파일이 포함되어 있습니다:

-   `auth.http` - 인증 API 테스트
-   `posts.http` - 게시물 API 테스트
-   `event.http` - 이벤트 API 테스트
-   `redis.http` - Redis 작업 테스트
-   `rabbitmq.http` - RabbitMQ 메시징 테스트
-   `restful.http` - 일반 REST API 테스트

## 🔄 마이그레이션 노트 (Java 8 / Spring Boot 2.1 → Java 25 / Spring Boot 4.1)

-   `javax.servlet`, `javax.mail` → `jakarta.servlet`, `jakarta.mail`
-   `WebSecurityConfigurerAdapter` 제거 → `SecurityFilterChain` 빈 + 람다 DSL, `@EnableGlobalMethodSecurity` → `@EnableMethodSecurity`
-   스타터 이름 변경: `spring-boot-starter-web` → `spring-boot-starter-webmvc`, `spring-boot-starter-aop` → `spring-boot-starter-aspectj`
-   프로퍼티 변경: `spring.redis.*` → `spring.data.redis.*`, `spring.data.mongodb.*` → `spring.mongodb.*`, `spring.resources.*` → `spring.web.resources.*`, `spring.rabbitmq.publisher-confirms` → `spring.rabbitmq.publisher-confirm-type`
-   `RedisConfig` 가 자동 구성된 `RedisConnectionFactory` 를 사용하도록 변경되어 `spring.data.redis.*` 설정이 실제로 적용됨
-   `mysql:mysql-connector-java` → `com.mysql:mysql-connector-j`
-   JJWT 0.9 → 0.13 (`jjwt-api`/`jjwt-impl`/`jjwt-jackson`). HS512 키 길이 요구사항(512bit 이상)에 맞춰 서명 키가 변경되어 **기존에 발급된 토큰은 더 이상 유효하지 않습니다.**
-   Fastjson 1.x → Fastjson2, Apache HttpClient 4 → HttpClient 5
-   JUnit 4 → JUnit Jupiter, Maven 3.6.0 → 3.9.16 (script-only Maven Wrapper)
