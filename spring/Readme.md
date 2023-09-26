# Spring Framework

- [스프링 홈페이지](https://spring.io)
- 스프링은 자바를 조금 더 객체지향적으로 쉽고, 생산성 있게 만들어주는 프레임워크
- 스프링의 핵심 개념
  - IoC (Inversion of Control) : 제어의 반전
  - DI(Dependency Injection) : 종속성 주입
- Spring Boot -
  - 자바 프로그래밍(스프링) 작업에 접근하는 방식을 획기적으로 간소화
- [Spring Initializer](https://start.spring.io)
  - Project (빌드 도구를 선택) : Gradle-Groovy
  - Language (JVM 언어를 선택) : Java
  - Spring Boot (스프링부트 버전을 선택)
    - 3버전 이후는 자바 17버전 이상 사용
    - 3.1.4
  - Project Metadata
    - group : (일반적으로 도메인 주소를 뒤집어 사용)
    - artifact : 프로젝트 이름
    - packaging : Jar
    - Java : 17
  - Dependency
    - 선택하지 않음
    - GENERATE (생성하면 압축파일 다운로드)
  - 압축받은 파일을 풀고 -> IDE로 프로젝트 폴더를 열기 

## 프로젝트 폴더 구조
```
├─.gradle
│ 
├─.idea
├─gradle
│  └─wrapper
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─busanit
    │  │          └─spring
    │  └─resources
    └─test
        └─java
            └─com
                └─busanit
                    └─spring
│   .gitignore : 깃 저장소에서 제외될 목록
│   **build.gradle : 그레이들의 빌드 설정, 의존성**
│   settings.gradle : 멀티 프로젝트 설정 시 사용
```

- src : 소스코드가 위치하는 디렉토리
  - main : 주요 소스코드
    - **java : 자바 소스코드 파일이 위치
      - 그룹명.아티팩트명
    - ~~(기타언어) kotlin : 다른 언어 소스파일이 위치~~
    - resources : 설정 파일, 이미지 파일 등
  - test : 테스트 코드가 위치
    - main과 유사한 폴더 구조를 가짐
- build : 빌드 시 생성되는 모든 파일 및 폴더
- gradle : 빌드 도구가 저장되는 디렉토리
- .gradle : 빌드 도구 캐시파일

- 실행 설정
  - 파일 > 설정 > 빌드, 실행, 배포 > Gradle > Gradle 프로젝트>
  - 다음을 사용하여 빌드 및 실행 > IntelliJ

## 스프링 = 객체 컨테이너 = IoC 컨테이너
- AnnotationConfigApplicationContext
  - 자바 애노테이션을 통해 Bean 객체 설정 정보를 불러온다.
- XmlConfigApplicationContext
  - XML을 통해서 Bean 객체 설정 정보를 가져온다

## 스프링 IoC 컨테이너 용어
- Bean : 빈 또는 빈 오브젝트. 스프링이 IoC 방식으로 관리하는 오브젝트 
  - 스프링에서 사용하는 모든 오브젝트가 Bean 아니다.
  - 스프링이 직접 생성과 제어를 담당하는 오브젝트
- Bean Factory : 스프링의 IoC를 담당하는 핵심 컨테이너
  - Bean을 등록하고, 생성하고, 조회하고, 돌려주고, 부가적인 기능을 추가.
  - getBean() 등의 메소드가 정의되어있다.
- Application Context : Bean 팩토리를 확장한 IoC 컨테이너
  - 기본 기능은 Bean Factory와 동일(상속)
  - 실제 스프링에서는 Context를 더 많이 사용(Bean Factory 용어로 부를 수 있음)
- Configuration : 스프링 Bean Factory 가 IoC 적용하기 위한 메타 정보
  - 앱 오브젝트를 생성하고 구성할 때 사용
  - 애플리케이션의 전체 그림이 그려진 형상정보 또는 blueprint
- Container : => Bean Factory, (AppContext)를 가리키는 것
  - 스프링 컨테이너라고 부른다.
  - 스프링에 Bean을 등록한다. => 환경 정보, 컨테이너에 객체 등록

- ## 스프링 프레임워크 :
  - ### 어플리케이션 컨텍스트를 포함한 모든 기능을 통틀어 말함.

- #### 스프링의 삼각형
  - 제어의 역전(IoC) / 의존성 주입(DI)
  - AOP : 관점 지향 프로그래밍
    - 부가적인 기능을 독립적으로 모듈화 하는 프로그래밍 모델
  - 서비스 추상화
    - 스프링은 환경이나, 서버, 특정 기술에 종속되지 않고
    - 유연한 애플리케이션을 만들 수 있다.