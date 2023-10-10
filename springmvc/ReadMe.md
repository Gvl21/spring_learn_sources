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