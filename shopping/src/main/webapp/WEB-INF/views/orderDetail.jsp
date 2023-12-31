<%@ page import="java.util.List" %>
<%@ page import="com.example.shopping.dto.order.OrderDetailDto" %>
<%@ page import="com.example.shopping.dto.order.OrderInfoDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>주문 상세 조회</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&amp;display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/static/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/static/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/static/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/static/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/static/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>

<body>
<!-- Page Preloder -->
<div id="preloder" style="display: none;">
    <div class="loader" style="display: none;"></div>
</div>
<!-- Header Section -->
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
                                <a href="/user/my-page/order-set">Back To Order List</a>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="continue__btn update__btn">
                                <a href="/order/cancel/${requestScope.orderSetId}">Cancel Order</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
    <jsp:include page="common/footer.jsp"></jsp:include>

</div>
<!-- Shopping Cart Section End -->

<!-- Js Plugins -->
<script src="/static/js/jquery-3.3.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.nice-select.min.js"></script>
<script src="/static/js/jquery.nicescroll.min.js"></script>
<script src="/static/js/jquery.magnific-popup.min.js"></script>
<script src="/static/js/jquery.countdown.min.js"></script>
<script src="/static/js/jquery.slicknav.js"></script>
<script src="/static/js/mixitup.min.js"></script>
<script src="/static/js/owl.carousel.min.js"></script>
<script src="/static/js/main.js"></script>


</body>
</html>


