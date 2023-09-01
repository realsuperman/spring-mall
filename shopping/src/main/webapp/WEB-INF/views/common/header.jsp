<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="code.jsp" %>
<%@include file="uploadPath.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../../static/main-page/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/style.css" type="text/css">
    <link rel="stylesheet" href="../../../static/main-page/css/main-custom-style.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
  <header class="header">
    <div class="header__top" style="padding-top: 16px; height: 48px;">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-md-7">
            <c:choose>
              <c:when test="${login_user == null || login_user.isAdmin == 0}">
                <div class="header__top__left">
                  <p>멤버십 별, 할인 혜택이 적용됩니다!</p>
                </div>
              </c:when>
            </c:choose>
          </div>
          <div class="col-lg-6 col-md-5">
            <div class="header__top__right">
              <div class="header__top__links">
                <c:choose>
                  <c:when test="${login_user == null}">
                    <a href="/user">Login/SignIn</a>
                  </c:when>
                  <c:otherwise>
                    <c:choose>
                      <c:when test="${login_user.isAdmin == 1}">
                        <p style="color: #FFFFFF">${login_user.userName} 님 &nbsp;&nbsp;&nbsp;&nbsp;
                      </c:when>
                      <c:otherwise>
                        <p style="color: #FFFFFF">${login_user.userName}님, 어서오세요 <${grade}> &nbsp;&nbsp;&nbsp;&nbsp;
                      </c:otherwise>
                    </c:choose>
                      <span><a href="/logout">LOGOUT</a></span>
                      </p>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 1. 로고 -->
    <div class="container">
      <div class="row">
        <div class="col-lg-3 col-md-3">
          <div class="header__logo">
            <a href="/"><img src="../../../static/main-page/img/롯데온%20LOGO.png" alt=""></a>
          </div>
        </div>
        <div class="col-lg-6 col-md-6"> <!-- 그리드 공간 때문에 넘겨두기 -->
          <nav class="header__menu mobile-menu">
          </nav>
        </div>
        <div class="col-lg-3 col-md-3">
          <c:choose>
            <c:when test="${login_user.isAdmin == 0}">
              <div class="header__nav__option">
                <a href="my-page"><img src="../../../static/main-page/img/icon/마이롯데.png" alt=""></a>
                <a href="/cart"><img src="../../../static/main-page/img/icon/장바구니.png" alt=""></a>
              </div>
            </c:when>
          </c:choose>
        </div>
      </div>
      <c:choose>
        <c:when test="${login_user == null || login_user.isAdmin == 0}">
          <!-- 2. 카테고리 DROP-DOWN     -->
          <div class="hoverClass"  id = "menuBody"  style="position: absolute;   z-index: 2;"><span style="text-decoration: underline #D34640 3.5px; font-weight: 700;
 font-size: 30px">Menu</span></div>
        </c:when>
      </c:choose>

    </div>
  </header>
  <%-- header script   --%>
  <script src="../../../static/js/header-script.js"></script>

  <script src="../../../static/main-page/js/jquery-3.3.1.min.js"></script>
  <script src="../../../static/main-page/js/bootstrap.min.js"></script>
  <script src="../../../static/main-page/js/jquery.nice-select.min.js"></script>
  <script src="../../../static/main-page/js/jquery.nicescroll.min.js"></script>
  <script src="../../../static/main-page/js/jquery.magnific-popup.min.js"></script>
  <script src="../../../static/main-page/js/jquery.countdown.min.js"></script>
  <script src="../../../static/main-page/js/jquery.slicknav.js"></script>
  <script src="../../../static/main-page/js/mixitup.min.js"></script>
  <script src="../../../static/main-page/js/owl.carousel.min.js"></script>
  <script src="../../../static/main-page/js/main.js"></script>
  <script src="../../../static/js/jquery-3.3.1.min.js"></script>

</body>



</html>
