<%@ page import="com.bit.shoppingmall.dto.OrderDetailDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bit.shoppingmall.dto.OrderInfoDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데 ON : 주문 상세 조회</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&amp;display=swap"
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
</head>

<body>
<!-- Page Preloder -->
<div id="preloder" style="display: none;">
    <div class="loader" style="display: none;"></div>
</div>
<!-- Header 시작 -->
<jsp:include page="common/header.jsp"></jsp:include>
<div style="position: relative; z-index: 1;     margin-top: 44px;">
    <jsp:include page="common/mypageHeader.jsp"></jsp:include>

    <!-- Shopping Cart Section Begin -->
    <section class="shopping-cart spad">
        <div id="orderInfo">
            <div class="container">
                <h4><b>Order Info</b></h4>
            </div>

            <div class="container">
                <div class="row">
                    <div class="p-3">
                        <table>
                            <thead>
                            <tr>
                                <th>Order Date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- TODO : 구분자 T -->
                            <tr>
                                <td>${requestScope.orderInfo.orderTime}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="p-3">
                        <table>
                            <thead>
                            <tr>
                                <th>Order Address</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${requestScope.orderInfo.orderAddress}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="p-3">
                        <table>
                            <thead>
                            <tr>
                                <th>Phone Number</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${requestScope.orderInfo.orderPhoneNumber}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <%--    <div class="container">--%>

        <div id="orderTable">
            <div class="container p-3">
                <h4><b>Order Detail</b></h4>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="shopping__cart__table">
                            <table>
                                <thead>
                                <tr>
                                    <th>Item Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.orderDetailList}" var="orderDetailDto" varStatus="state">
                                    <tr>
                                        <td class="product__cart__item">
                                            <div class="product__cart__item__text">
                                                    ${orderDetailDto.itemName}
                                            </div>
                                        </td>
                                        <td class="quantity__item">
                                            <div class="product__cart__item__text">
                                                    ${orderDetailDto.itemQuantity}
                                            </div>
                                        </td>
                                        <td class="product__cart__item__text">
                                                ${orderDetailDto.buyPrice}
                                        </td>
                                        <td class="product__cart__item__text">
                                                ${orderDetailDto.statusName}
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="container">
                            <div class="row">
                                <div class="p-2">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>Total Buy Price</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>${requestScope.orderSetTotalBuyPrice}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="continue__btn">
                                    <a href="${pageContext.request.contextPath}/orderSetList">Back To Order List</a>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="continue__btn update__btn">
                                    <a href="/orderCancel?orderSetId=${requestScope.orderSetId}">Cancel Order</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
</div>

<!-- Js Plugins -->
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


</body>
</html>


