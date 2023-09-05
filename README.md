# spring-mall

### 🛍️ Project _ 쇼핑몰

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
  🙋‍♀️ 일반 유저 전용

- 상품 장바구니 추가/수정/삭제
- CART TOTAL로 최종 주문 건 확인 가능
- 페이지네이션

### 5. 주문 관련 기능 
  🙋‍♀️ 일반 유저 전용

- 주문 및 주문 취소 
- 주문 목록 확인 하기
- 주문 디테일 확인 하기
- 카카오 페이 API

### 6. 관리자 관련 기능 
  🧑‍💼어드민 유저 전용
- 상품 추가
- 상품 상태 관리
- 재고 관

# 3. 프로젝트 설계 및 개발

## 1. 기술 스택
- **OS**

<img src="https://img.shields.io/badge/Windows-0078D6?style=flat&logo=Windows&logoColor=white"> 

- **Back**

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"> ![](https://img.shields.io/badge/Java-007396?style=flat&logo=OpenJDK&logoColor=white") <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white">

- **Front**

<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white">  ![](https://img.shields.io/badge/JSP-007396?style=flat&logo=OpenJDK&logoColor=white") <img src="https://img.shields.io/badge/jquery-0769AD?style=flat&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white">

- **DB**

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-007054?style=flat&logo=MyBatis&logoColor=white">

- **Cloud**
  
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat&logo=Amazon AWS&logoColor=white">  <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=Amazon EC2&logoColor=white"> <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat&logo=Amazon RDS&logoColor=white"> 

- **기타**
  
<img src="https://img.shields.io/badge/RestTemplate-6DB33F?style=flat&logo=Spring Boot&logoColor=white">  <img src="https://img.shields.io/badge/SpringSecurity-DB33F?style=flat&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/AJAX-5A29E4?style=flat&logo=AJAX&logoColor=white"> <img src="https://img.shields.io/badge/kakaoAPI-FFCD00?style=flat&logo=kakao&logoColor=white"> <img src="https://img.shields.io/badge/intellijidea-000000?style=flat&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/Git-F05032?style=flat&logo=Git&logoColor=white"> <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white">


## 2. 서비스 아키텍처

![spring-mall-drawio](https://github.com/realsuperman/spring-mall/assets/66711073/f0b391d3-b623-42d7-ad57-3fcd6cb498d2)


## 3. ERD

![Untitled](https://github.com/realsuperman/shopping-mall/assets/25142537/b89db01b-0b66-4304-ab48-f83c601653e9)



# 4. 고민한 부분
**✔️ 프론트에서 form JSON 데이터 POST 요청을 보내는데, 컨트롤러에서 RequestParam ?**

Body가 아닌 Param 으로 받는 것이 어색하나, 
이때, AJAX 요청으로 수정하기 힘든 상황이였기에 form 으로 보낸다는 가정 하에서 Param으로 받아, 진행하였다.
`@RequestParam(value = "orderItemDtoList") String request` 



# 5. 회고록

### 이우엽 
- 변수이름을 고민을 많이 하는게 좋다는 생각을 했다.
- jsp에서 $("태그명").val() 방식으로 값을 가져오는 경우가 많았는데 // 증복이 될까봐 매번 변수이름을 안겹치게 정한게 나중에는 독이 됐다.
- 프로젝트를 통해 RESTFUL 메서드에 대한 정확한 이해와 적용을 할 수 있었다.
- ajax적용을 많이 해보면서 ajax에 대해 이해할 수 있었다.
- 중복코드에 대한 공통화를 고려했었더라면 더 좋았을 거 같다.
- 페이지네이션을 스프링 부트에서는 만들어진 걸 간단히 가져다썼는데 직접 구현해보면서 내부 동작 원리를 잘 생각해 볼 수 있어서 좋았다.
- 디버깅을 해보면서 문제의 원일을 찾는 역량이 늘었다.
- 페이지네이션을 자바로 구현할 생각만 했는데 다른 분은 JS로 하신걸 보고 더 쉽게 가능하다는 걸 알게 됐다.

### 전종민
- 프로젝트 초반에 JSP와 HttpServlet의 동작 방식을 파악하는 데 애를 먹었다. 직관적으로 request에 값을 넣어서 준다는게 도저히 이해가 안갔었다. jsp에 이 값을 넣어 그려달라는 요청의 의미로 request라고 이해했다.
- HttpServlet의 doPost 내부에서는 getParameter를 사용할 수 있도록 서블릿 컨테이너가 처리해주지만, doPut 내부에서는 getParameter를 사용할 수 있도록 서블릿 컨테이너가 처리해주지 않아 디버깅에 애를 먹은 적이 있다. ajax의 data 영역에 담아 보낸 데이터를 읽어올 때는 항상 getInputStream으로 받도록 하자.
- 1년 넘게 잊고 있던 Post-Redirect-Get 패턴을 다시금 상기할 수 있었다.
- 도메인 단위로 설계는 했는데, 메서드 설계나 dto 단계까지 설계하지 못해 단일 행 mapper + 서비스 단위의 for-loop으로 코드를 작성하거나, 1, 2개 필드만 다른 중복된 dto를 만들어 사용하게 됐다. 설계에 충분한 시간을 들여 작은 단위까지 설계해야겠다는 생각이 들었다.
- 외부 API를 사용할거라면 DB 설계 시부터 이를 고려해야 한다는 생각이 들었다.
- 프로젝트 초반에는 dao와 service 테스트 코드도 잘 작성했지만 프로젝트를 진행하다 보니 테스트 코드를 작성하지 못했다.
- Spring과 CSR의 소중함을 느꼈다. jsp 파일 내에 script 넣고, style 넣고 개판으로 짰다.
- 업무가 다소 과중했다…

### 최소영
- 쇼핑몰 프로젝트를 처음 진행하는 만큼 해당 도메인에서 어떤 설계, 어떤 개발이 이뤄져야 하는지 알게 되어 좋았습니다. 
- 개발 전 구조화 설계가 얼마 만큼 중요한지, 또, 어떻게 이뤄져야 하는지 알게 되었습니다. 
- 처음 기획, 설계에서 달라진 부분 또, 구현하지 못한 부분도 있어, 아쉽기도 합니다. 

### 최성훈
- 백엔드 설계는 괜찮게 했는데 프론트 설계가 아쉬웠다.
ex) 공통페이지에 header footer만 두고 jsp자체를 끼워넣게 설계했으면 더 좋았을건데 그러지 못했다.
카카오쪽에서 값을 몇페이지에 걸쳐 유지했어야하는 상황이 발생했는데 해당 방법을 미리 가져갔다면 이 문제를 피할 수 있었는데 그러지 못해 Session Storage를 쓰는 상황이 발생했다.
- 원래 이런식의 설계가 일반적인데 왜 이렇게 하는건지를 이제 알았다.
- 전체적인 개발 스케줄을 고려하지 못하고 기능을 확장하면서 조금의 아쉬운 점들이 생겼다.
- 원했던 공통화 기능은 잘 작성했고 잘 동작해서 개인적으로는 만족스럽다.

### 최창효
- 이론적으로 알고 있던 내용도 막상 직접 개발할때는 지켜지지 않는 게 많다.
