# 드론 AIoT 5기 — 쇼핑몰 실습 코드

**오후반(안연수) 실습 코드 저장소.** 모놀리식 웹앱 → 도커 → GCP 배포.

## 학생 사용법

```
git pull
```

**수업 중에 코드를 받아 적지 않습니다.** 매일 이 저장소에서 그날 코드를 받습니다.

> **지금 이 저장소에는 골격만 있습니다. 정상입니다.**
> 코드는 그날 수업에서 올라갑니다.

## 개발 환경

| | |
|---|---|
| OS | Windows + **WSL2 (Ubuntu)** — 모든 작업은 WSL 안에서 |
| Java | **JDK 17** ★ 반드시 17. 상위 버전이면 Gradle 이 죽습니다 |
| 빌드 | Gradle **래퍼를 씁니다.** 따로 깔지 않습니다 |
| DB | **MySQL 8.0** — 도커 컨테이너 |
| IDE | IntelliJ (윈도우) + 터미널은 WSL |

## 설치 (1~4일차)

**[`setup/README.md`](setup/README.md)** 순서대로 개발 환경을 만듭니다.

```bash
bash setup/install.sh        # 윈도우: WSL2 Ubuntu 창에서
bash setup/install-mac.sh    # macOS
```

램 16GB 에서 버티는 법은 **[`setup/WSL_운영.md`](setup/WSL_운영.md)** 에 있습니다.

## 실행

**① MySQL 을 띄웁니다** (4일차)

```bash
docker run -d --name shop-mysql -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=shop1234 \
  -e MYSQL_DATABASE=shop \
  mysql:8.0
```

**② 앱을 실행합니다**

```bash
./gradlew bootRun
```

**③ 브라우저에서 확인합니다**

| 주소 | 무엇 |
|---|---|
| http://localhost:8080 | 화면 (`static/index.html`) |
| http://localhost:8080/swagger-ui.html | API 를 눌러보는 화면 |

## 폴더 구조

```
src/main/java/com/dwacademy/shop/
├── controller/     REST 컨트롤러
├── service/        비즈니스 로직
├── repository/     JPA 리포지토리
├── entity/         JPA 엔티티
├── enums/          열거형
├── model/          요청·응답 DTO   ← 'dto' 가 아니라 model 입니다
└── config/         설정

src/main/resources/
├── application.yml
└── static/         ★ React 빌드 결과가 들어갈 자리 (15일차)
```

**명명 규칙**: `UserRequest`·`UserResponse`(model) · `UserStatus`(enums) ·
`UserController`·`UserService`·`UserRepository`.

## React 는 왜 별도 서버가 아닌가

`npm run build` 결과를 `src/main/resources/static/` 에 넣으면 **스프링이 그대로 서빙**합니다.

```
localhost:8080              → React 화면
localhost:8080/api/products → 스프링 API
```

**한 포트, 한 컨테이너.** 출처가 같아서 **CORS 가 아예 없습니다.**
nginx 를 따로 띄우면 컨테이너가 3개가 되고 CORS·프록시 설정이 붙습니다.

## 최종 목표 (18일차 · 10/7)

```
내 노트북에서 만든 앱  →  도커 이미지  →  GCP VM  →  남이 볼 수 있는 주소
```

## 진도

| 일자 | 내용 |
|---|---|
| 1~4 (9/9~9/14) | OT · **리눅스/WSL** · Docker · MySQL 컨테이너 — 코드 없음 |
| 5 (9/15) | **프로젝트 골격** — 이 저장소를 처음 엽니다 |
| 6~8 | 회원 — 엔티티 · 회원가입 · **BCrypt** · 로그인 |
| 9~11 | 상품 CRUD + React 화면 · 통합 점검 |
| 12 (9/28) | 복습 & 중간 점검 |
| 13~15 | **Docker** — Dockerfile · compose · React 를 static 으로 |
| 16 (10/2) | GitLab — 브랜치 · 이슈 · README |
| 17~18 | **GCP 배포** ← 결승선 |
| 19~21 | 면접 대응 · 리뷰 · 수료 |