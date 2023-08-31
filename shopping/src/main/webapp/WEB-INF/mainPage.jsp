<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="common/code.jsp" %>
<%@include file="common/uploadPath.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데 ON</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../static/main-page/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/style.css" type="text/css">
    <link rel="stylesheet" href="../../static/main-page/css/main-custom-style.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

</head>
<body>

    <!-- Header Section Begin -->
    <jsp:include page="common/header.jsp"></jsp:include>
    <!-- Header Section End -->

    <%-- BODY   --%>
    <div style="position: relative; z-index: 1;     margin-top: 44px;">
        <!-- 인기 상품 시작 -->
        <div class="hero__items set-bg" data-setbg="../../static/main-page/img/hero/hero-1.jpg">
            <div class="col-lg-12">
                <div class="section-title">
                    <br>
                    <span>a week's popularity</span>
                    <h2>Best Seller</h2>
                </div>
            </div>
            <section class="shop spad">
                <div class="container">
                    <div class="row" id = "bestSeller"></div>
                </div>
            </section>
        </div>
        <!-- 인기 상품 끝 -->

        <%-- 최신 상품  시작   --%>
        <section class="hero">
            <div class="hero__slider owl-carousel">

                    <c:set var = "downPrefix" value = "https://firebasestorage.googleapis.com/v0/b/shoppingmall-c6950.appspot.com/o/"/>
                    <c:set var = "downSuffix" value = "?alt=media"/>

                    <section class="shop spad">
                        <div class="container">
                            <c:forEach items="${itemList}" var = "items" varStatus="status">
                                <div class="row">
                                    <div class="col-lg-3 product__item__text">
                                        <h5 style="display:inline">${categoryNames[status.index]}</h5> 의 최신상품
                                    </div>

                                    <div class="col-lg-9 col-md-6 col-sm-6">
                                        <div class="shop__product__option__right">
                                            <a href="item?categoryId=${categoryIds[status.index]}&page=1">더 보기</a>
                                        </div>
                                    </div>

                                    <div class="col-lg-12">
                                        <div class="row">
                                            <c:forEach items="${items}" var = "item">
                                                <div class="col-lg-3 col-md-6 col-sm-6">
                                                    <div class="product__item">
                                                        <a href="itemDetail?itemId=${item.itemId}">
                                                            <img class="product__item__pic set-bg" src ="${downPrefix}${fn:split(item.itemImagePath,';')[0]}${downSuffix}">
                                                        </a>
                                                        <div class="product__item__text">
                                                            <h6>${item.itemName}</h6>
                                                            <a href="itemDetail?itemId=${item.itemId}" class="add-cart">상품 상세보기</a>
                                                            <h5><fmt:formatNumber value="${item.itemPrice}" />원</h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </section>
                </div>
        </section>
        <!-- 최신 상품 끝 -->

        <jsp:include page="common/footer.jsp"></jsp:include>


    <%-- best script   --%>
    <script src="../../static/js/best-seller-script.js"></script>

    <script src="../../static/main-page/js/jquery-3.3.1.min.js"></script>
    <script src="../../static/main-page/js/bootstrap.min.js"></script>
    <script src="../../static/main-page/js/jquery.nice-select.min.js"></script>
    <script src="../../static/main-page/js/jquery.nicescroll.min.js"></script>
    <script src="../../static/main-page/js/jquery.magnific-popup.min.js"></script>
    <script src="../../static/main-page/js/jquery.countdown.min.js"></script>
    <script src="../../static/main-page/js/jquery.slicknav.js"></script>
    <script src="../../static/main-page/js/mixitup.min.js"></script>
    <script src="../../static/main-page/js/owl.carousel.min.js"></script>
    <script src="../../static/main-page/js/main.js"></script>

</body>
</html>
