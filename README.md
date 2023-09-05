# 🛍️ Project _ 쇼핑몰

<span style="color:gray">*__Project Summary__*</span>
 
✔️ **프로젝트 명** 

쇼핑몰 프로젝트

**✔️ 프로젝트 기간**

2023.08.14 ~ 2023.09.05

**✔️ 홈페이지 명**

롯데 ON

✔️ **프로젝트 설명** 

쇼핑몰 프로젝트 - 스프링 부트 전환

# 1. 팀 소개

| 이름 | 역할 | 깃 주소 |
| --- | --- |--- |
| 이우엽 | | https://github.com/leewooyup |
| 전종민 | | https://github.com/wakkpu |
| 최소영 | | https://github.com/cso6005 |
| 최성훈 | | https://github.com/realsuperman |
| 최창효 | | https://github.com/qwerty1434 |

# 2. 프로젝트 기획

## 1. 주요 기능

[기능 정의서](https://www.notion.so/0c6829c3752a4f699fc776851450e1be?pvs=21) 

### 1. 메인 페이지 관련 기능

- 헤더에서 카테고리로 보여주기
    - 드롭다운 방식의 헤더
- 인기 상품 아이템 보기
    - 대분류를 기준으로 소분류 상품을 보여줌
    - 옆으로 넘겼을 때 캐러셀처럼 돌아옴
- 최신 상품 아이템 보기
    - 접속할 떄 마다 매번 새로운 카테고리의 상품이 보이게 됨

### 2. 유저 관련 기능
- 회원가입, 로그인/아웃웃, 사용자 정보 조회 및 수정

### 3. 상품 조회 관련 기능

- 재고가 N개 이하로 떨어졌을 때 상품의 개수를 알려줌
- 재고 이하 수량 선택 불가


### 4. 장바구니 관련 기능 
  **🙋‍♀️ 일반 유저 전용**

- 상품 장바구니 추가/수정/삭제
- CART TOTAL로 최종 주문 건 확인 가능
- 페이지네이션

### 5. 주문 관련 기능 
  **🙋‍♀️ 일반 유저 전용**

- 주문 및 주문 취소 
- 주문 목록 확인 하기
- 주문 디테일 확인 하기
- 카카오 페이 API

### 6. 관리자 관련 기능 
  **🧑‍💼어드민 유저 전용**
  
- 상품 추가
- 상품 상태 관리
- 재고 관리

# 3. 프로젝트 설계 및 개발

## 1. 기술 스택
- **OS**

<img src="https://img.shields.io/badge/Windows-0078D6?style=flat&logo=Windows&logoColor=white"> 

- **Back**

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"> ![](https://img.shields.io/badge/Java-007396?style=flat&logo=OpenJDK&logoColor=white") <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/SpringSecurity-DB33F?style=flat&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-007054?style=flat&logo=MyBatis&logoColor=white">  <img src="https://img.shields.io/badge/kakaoAPI-FFCD00?style=flat&logo=kakao&logoColor=white">

- **Front**

<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white">  ![](https://img.shields.io/badge/JSP-007396?style=flat&logo=OpenJDK&logoColor=white") <img src="https://img.shields.io/badge/jquery-0769AD?style=flat&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white"> <img src="https://img.shields.io/badge/AJAX-5A29E4?style=flat&logo=AJAX&logoColor=white">

- **DB**

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"> 

- **Cloud**
  
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat&logo=Amazon AWS&logoColor=white">  <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=Amazon EC2&logoColor=white"> 

- **IDE**

<img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white">
 
- **협력 툴 및 프로젝트 관리**

 <img src="https://img.shields.io/badge/Git-F05032?style=flat&logo=Git&logoColor=white"> <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white"> <img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white">  <img src="https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white"> 


## 2. 서비스 아키텍처

![spring-mall drawio](https://github.com/realsuperman/spring-mall/assets/66711073/ddd1a007-fed4-4e9a-82d2-7c91dd0bb785)

## 3. ERD

![Untitled](https://github.com/realsuperman/shopping-mall/assets/25142537/b89db01b-0b66-4304-ab48-f83c601653e9)



# 4. 변경 작업
**✔️ 스프링 부트 적용**

**✔️ 스프링 시큐리티 적용**

**✔️ 코드 리팩토링**
* 중간에 화면을 동적으로 변경하는 로직 중 불필요하게 발생한 중복 코드 제거
* 불필요한 JOIN 제거
* 중첩된 구조의 코드 개선

**✔️ 카카오 API 요청 방법 변경**
* 기존에 HTTP URL Connection을 사용하던 외부 통신을 restTemplate요청으로 변경

**✔️ 컨트롤러 통합**
* HttpServlet에서 하나의 주소는 하나의 get 또는 post만 가지던 문제 해결





# 5. 회고록

### 이우엽 
- URL 매핑이 서블릿보다 훨씬 쉬워 좀 더 비즈니스 로직이나 공통화에 신경쓸 수 있었습니다.

### 전종민
- Spring Boot로 변환하면서 sqlsession이라던가, session에 관한 관리를 Spring Boot로 위임하면서 비즈니스 로직에 관한 것만 코드로 남아 코드의 양이 줄어 가독성이 좋아지고 수정하기에 용이했다.

### 최소영
- 스프링부트의 편안함을 느끼면서도, 전환하는 과정을 통해서 마냥 부트를 쓸 것이 아니라, 동작 원리를 이해하고 써야 한다는 것을 느꼈습니다. 

### 최성훈
- 스프링이 제공 해주는 여러가지 기능들을 사용하면서 스프링의 구조에 대해서 더 잘 이해할 수 있었습니다.

### 최창효
- 처음부터 Spring Boot프로젝트를 만들고 코드를 작업하는 방식이 아닌 다른 환경에서 이미 완성한 작업을 Spring Boot로 옮기는 과정이 낯설었고, 그 과정에서 새로운 내용을 많이 학습할 수 있었습니다.
