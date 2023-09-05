<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>주문 내역</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&amp;display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css">
</head>

<body>
<!-- Page Preloder -->
<div id="preloder" style="display: none;">
    <div class="loader" style="display: none;"></div>
</div>

<!-- Shopping Cart Section Begin -->
<section class="shopping-cart spad">
    <div id="orderInfo">
        <div class="container p-3 pb-5 pt-5">
            <h4><b>Order List</b></h4>
        </div>

        <%--    <div class="container">--%>
        <div id="orderTable">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="shopping__cart__table">

                            <c:choose>
                            <c:when test="${requestScope.consumerOrderSetList == null || requestScope.consumerOrderSetList.size() == 0}">
                                주문 내역이 존재하지 않습니다.
                            </c:when>
                            <c:otherwise>
                            <table>
                                <thead>
                                <tr>
                                    <th>Order Code</th>
                                    <th>Items</th>
                                    <th>Order Address</th>
                                    <th>Phone Number</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.consumerOrderSetList}" var="orderSetDto"
                                           varStatus="state">
                                    <tr>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__text">
                                                    ${orderSetDto.orderCode}
                                            </div>
                                        </td>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__text">
                                                <c:choose>
                                                    <c:when test="${orderSetDto.distinctItemCount > 1}">
                                                        ${orderSetDto.representative} 외 ${orderSetDto.distinctItemCount - 1}종
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${orderSetDto.representative}
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__text">
                                                    ${orderSetDto.orderAddress}
                                            </div>
                                        </td>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__text">
                                                    ${orderSetDto.orderPhoneNumber}
                                            </div>
                                        </td>
                                        <td>
                                            <div class="continue__btn">
                                                <a href="/user/my-page/order-set/${orderSetDto.orderSetId}">Order Detail</a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<!-- Shopping Cart Section End -->

<!-- Js Plugins -->
<script src="../../static/js/jquery-3.3.1.min.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/jquery.nice-select.min.js"></script>
<script src="../../static/js/jquery.nicescroll.min.js"></script>
<script src="../../static/js/jquery.magnific-popup.min.js"></script>
<script src="../../static/js/jquery.countdown.min.js"></script>
<script src="../../static/js/jquery.slicknav.js"></script>
<script src="../../static/js/mixitup.min.js"></script>
<script src="../../static/js/owl.carousel.min.js"></script>
<script src="../../static/js/main.js"></script>


</body>
</html>


