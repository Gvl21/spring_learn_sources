# Spring MVC

- Model
- View
- Controller
---
- 프론트 콘트롤러 패턴
  - DispatcherServlet : 웹 브라우저로부터 요청이 들어오면, 앞단에서 모든 연결과 요청을 담당한다.
    - 요청을 처리하기 위한 컨트롤러를 검색
      - HandlerMapping 빈 객체를 통해서 컨트롤러 검색
      - (Controller)
      - HanlderAdapter 컨트롤러 메서드에 알맞은 메서드를 호출
      - (Model)
      - ViewResolver 이름에 해당하는 View 객체를 찾아서 생성하고 리턴
      - (View)
      
## MVC 패턴이란
- 디자인 패턴의 일종
- Model : 데이터를 관리하는 역할
- View: 웹페이지를 화면에 보여주는 역할
  - JSP, Thymeleaf, mustache, freemarker
  - (FrontEnd) React.js, vue.js
- Controller : 클라이언트의 요청을 받아 관리하는 역할
  - FrontController (DispatchServlet)를 통해서 다른 컨트롤러로 접근
![MVC](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/800px-MVC-Process.svg.png)

- View 탬플릿의 생성 위치
  - src > main > resources > templates
- 정적 파일의 위치
  - src > main > resources > static
- 컨트롤러의 생성 위치
  - src > main > java > 기본 패키지 > controller

## H2 DataBase
- 설치가 필요없고, 용량이 가벼워서, 개발용 로컬 DB로 사용하기 좋은 DBMS
- 자바 기반 오픈소스 관게형 RDBMS
- in-memory DB 기능을 지원
- 2MB 내외의 저용량 데이터베이스
- 표준 SQL 지원
- 로컬 및 테스트 환경에서 많이 사용

### H2 콘솔 보기 순서
1. application.properties 에서 설정을 추가 (콘솔보기)
  - `spring.h2.console.enabled=true`
2. 스프링 실행파일을 재시작 한 후 브라우저에서
   - `http://localhost:8080/h2-console` 접속
3. 실행 로그에서 `H2ConsoleAutoConfiguration` 부분을 찾아 URL 주소를 복사 (URL은 시작할 때마다 변경된다.)
4. JDBC URL 부분에 URL을 붙여넣기
5. [Connect]
- URL 고정
  - application.properties 파일에서
    - `spring.datasource.url=jdbc:h2:mem:testdb`
  - 을 추가한 후, 브라우저 콘솔 URL에 `jdbc:h2:mem:testdb`를 입력하면 앞으로 재시작하더라도 주소 고정
## form 데이터 전달
- HTML 폼 데이터에서 전달 받은 데이터는 name 속성의 이름에 맞춰 http로 전달
- 컨틀롤러가 객체(DTO)에 담아 받는다.
- DTO를 DB에 저장
  - JPA를 사용하는 경우
    - Entity 객체 : 자바 객체를 DB가 이해할 수 있게 만든 것
    - Repository : 엔티티가 DB테이블에 접근, 저장, 관리 할 수 있게 하는 인테페이스
- 외부에서 만들어진 객체(Controller에 Repository)를 DI(의존성 주입)

## Lombok
- 프로젝트 롬복의 축약어
- 자바 코드를 더 간단하고 간결하게 사용하게 만들어주는 라이브러리
  - 애노테이션 기반으로 코드생성
  - 코드의 중복을 제거 : 코드의 가독성 향상, 실수 방지
  - @Data : getter, setter, equals, hashCode, toString까지 자동으로 생성
  - @Getter
  - @Setter
  - @ToString
  - @AllArgsConstructor : 모든 필드를 파라미터로 받는 생성자
  - @NoArgsConstructor : 아무 필드도 파라미터로 받지 않는 기본 생성자
  - @Builder : 빌더 패턴을 쉽게 적용할 수 있게 빌더 클래스 생성
  - @Slf4j : 로깅 모듈
    - 등 다양한 애노테이션을 지원

## 데이터 조회 과정
1. 사용자가 URL 요청 (Http Request(Method Get or Post))
2. 컨트롤러가 요청을 받아 데이터 정보를 Repository에 전달
3. 리파지토리는 데이터를 DB에 조회 요청
4. DB는 해당 데이터를 찾아와서 Entity로 반환
5. 반환된 엔티티는 모뎅를 통해 뷰 템플릿으로 전달
6. 뷰 페이지가 완성되어 사용자 화면에 출력

- @PathVariable : URL 요청을 동적으로 받는 경로 매개 변수
- findById() : JPA 제공 메서드 id 기준으로 데이터 검색 Optional로 반환
- findByAll : JPA 메서드 엔티티의 모든 데이터를 조회하고, Iterable로 반환
- {{#object}} {{/object}} : 
  - 머스태치 문법, 해당 범위 내에 데이터를 (객체의 필드) 사용 가능, 
  - 반복 가능한 객체일 경우(복수 객체) 해당 범위 코드가 반복됨
  - Entity에 기본 생성자가 있어야 객체 반복 및 필드 사용 가능
- Java Bean
  - 특정 형태의 클래스 (DTO, VO)
    - public 접근 가능한 기본 생성자
    - 모든 필드는 private
    - getter / setter
  - Spring Bean 과는 다름 (스프링빈 : 스프링에서 관리하는 객체)

- CommandLineRunner 인터페이스
  - 스프링 부트 앱 구동 시점에 코드를 실행시킬 때 사용
  - @Component 애노테이션 선언으로 컴포넌트 스캔
  - 구동 시점에 run 메소드를 실행