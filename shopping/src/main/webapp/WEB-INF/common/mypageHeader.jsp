<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>MyPage</title>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데온 - 마이페이지</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../static/css_test/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../static/css_test/style.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
      .left-arrow:hover, .right-arrow:hover {
        cursor: pointer;
      }
      .input-val {
        width: 45px;
        border: 1px #EEEEEE solid;
        border-radius: 5px;
        text-align: right;
        padding: 0 6px;
      }
    </style>
</head>
<body>


<c:set var="pathUri" value="${pageContext.request.requestURL.substring(pageContext.request.requestURL.lastIndexOf('/') + 1)}" />

  <section class="breadcrumb-option">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="breadcrumb__text">

            <p style=" font-size: 40px">MY PAGE</p>

            <div class="mypage-tab" style="color: #0b0b0b">
                <c:choose>
                    <c:when test="${pathUri == 'myPage.jsp' || pathUri == 'myPageUpdate.jsp' }" >
                        <a href="/my-page" style="color: #0b0b0b ; font-size: 25px " >MY INFO</a> <br>
                        <a href="/orderSetList" style="color: #6b6b6b ; font-size: 25px " ><span>MY ORDER</span></a> <br>
                    </c:when>
                    <c:when test="${pathUri == 'orderSetList.jsp' || pathUri == 'orderDetail.jsp' || pathUri == 'orderCancel.jsp'}" >
                        <a href="/my-page" style="color: #6b6b6b ; font-size: 25px " >MY INFO</a> <br>
                        <a href="/orderSetList" style="color:  #0b0b0b ; font-size: 25px " ><span>MY ORDER</span></a> <br>
                    </c:when>
                </c:choose>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>



<!-- Breadcrumb Section End -->
<script src="../static/js_test/jquery-3.3.1.min.js"></script>
<script src="../static/js_test/bootstrap.min.js"></script>
<script src="../static/js_test/jquery.nice-select.min.js"></script>
<script src="../static/js_test/jquery.nicescroll.min.js"></script>
<script src="../static/js_test/jquery.magnific-popup.min.js"></script>
<script src="../static/js_test/jquery.countdown.min.js"></script>
<script src="../static/js_test/jquery.slicknav.js"></script>
<script src="../static/js_test/mixitup.min.js"></script>
<script src="../static/js_test/owl.carousel.min.js"></script>
<script src="../static/js_test/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>



</body>
</html>
