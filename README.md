# demo-http-filter
사용자가 요청한 모든 HttpRequest / Response 로깅 담기
----
# 기본 구조 및 사용 라이브러리
---
* SpringBoot 2.4.2
* Lombok
* apache-commons.commons-lang3
* commons-io
* org.bouncycastle.bcprov-jdk16
---
## 실행 환경
* JDK14

### 실행 방법
DemoHttpFilterApplication 실행

## 호출하기
PostMan 혹은 curl 을 이용하여 테스트할 수 있으며, 아래와 같은 로그를 확인할 수 있다.
```
 {"request":{"method":"POST","url":"http://127.0.0.1:8080/api/post","headers":{"content-length":"0" ... }
```
1. METHOD: POST: http://127.0.0.1:8080/api/post
2. METHOD: GET: http://127.0.0.1:8080/api/get

---
