<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/commonScript.jsp" %>

<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>
<script src="../../static/js/jquery-3.3.1.min.js"></script>

<script>
    $(function () {
        let orderCode = "ORDERCODE";
        let orderInfoDto = {
            orderCode: orderCode,
            orderAddress: $("#orderAddress").val(),
            orderPhoneNumber: $("#orderPhoneNumber").val()
        };

        let orderItemDtoList = [];
        let orderItemNameList = [];
        <c:forEach items="${requestScope.orderItemDtoList}" var="orderItemDto" varStatus="state">
            orderItemDtoList.push({
                itemId: ${orderItemDto.itemId},
                cartId: ${orderItemDto.cartId},
                itemQuantity: ${orderItemDto.itemQuantity},
                itemPrice: ${orderItemDto.itemPrice},
                itemName: "${orderItemDto.itemName}"
            });
            orderItemNameList.push("${orderItemDto.itemName}");
        </c:forEach>

        let formData = {
            partnerOrderId: "",
            partnerUserId: "",
            itemName: "",
            quantity: 0,
            totalAmount: 0,
            taxFreeAmount: 0,
            orderItemDtoList: JSON.stringify(orderItemDtoList),
            orderInfoDto: JSON.stringify(orderInfoDto)
        }

        formData.partnerOrderId = orderCode;
        formData.partnerUserId = ${sessionScope.get('login_user').consumerId};
        formData.itemName = encodeURIComponent(orderItemNameList.join(', '));
        orderItemDtoList.forEach(function (orderItemDto) {
            formData.quantity += orderItemDto.itemQuantity;
            formData.totalAmount += (orderItemDto.itemPrice * orderItemDto.itemQuantity);
        });

        let discount_rate = <%= session.getAttribute("discount_rate") %>;

        formData.totalAmount -= formData.totalAmount * discount_rate;
        formData.totalAmount = Math.ceil(formData.totalAmount);
        formData.taxFreeAmount = formData.totalAmount;

        $("button[name='callKakaoPay']").on("click", function () {
            callKakaoPay(formData);
        });
    });
</script>

<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>주문 확인</title>

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
<section class="shopping-cart spad">
    <div id="orderInfo">
        <div class="container">
            <h5><b>구매자 정보</b></h5>
        </div>

        <!-- 사용자 이름 -->
        <div class="container">
            <div class="row">
                <div class="p-3">
                    <table>
                        <thead>
                        <tr>
                            <th>이름</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${sessionScope.get("login_user").userName}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- 사용자 이메일 -->
        <div class="container">
            <div class="row">
                <div class="p-3">
                    <table>
                        <thead>
                        <tr>
                            <th>이메일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${sessionScope.get("login_user").userEmail}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- 사용자 전화 번호 -->
        <div class="container">
            <div class="row">
                <div class="p-3">
                    <table>
                        <thead>
                        <tr>
                            <th>전화 번호</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${sessionScope.get("login_user").phoneNumber}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div id="destinationInfo">
        <div class="container">
            <h5><b>배송지 정보</b></h5>
        </div>

        <!-- 배송지 주소 -->
        <div id="address">
            <div class="container">
                <div class="row">
                    <div class="p-3">
                        <table>
                            <thead>
                            <tr>
                                <th>배송지</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="orderAddress"
                                           value="${sessionScope.get("login_user").address}">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- 배송지 전화 번호 -->
        <div id="phoneNumber">
            <div class="container">
                <div class="row">
                    <div class="p-3">
                        <table>
                            <thead>
                            <tr>
                                <th>전화 번호</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="orderPhoneNumber"
                                           value="${sessionScope.get("login_user").phoneNumber}">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--    <div class="container">--%>
    <div id="orderTable">
        <div class="container p-3">
            <h4><b>구매 상품 목록</b></h4>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="shopping__cart__table">
                        <!-- 상품명과 갯수 -->
                        <table>
                            <thead>
                            <tr>
                                <th>상품명</th>
                                <th>수량</th>
                                <th>가격</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.orderItemDtoList}" var="orderItemDto" varStatus="state">
                                <tr>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__text">
                                                ${orderItemDto.itemName}
                                        </div>
                                    </td>
                                    <td class="quantity__item">
                                        <div class="product__cart__item__text">
                                                ${orderItemDto.itemQuantity}
                                        </div>
                                    </td>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__text">
                                                ${orderItemDto.itemPrice}
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div id="paymentInfo" data-total-price="${totalBuyPrice}">
            <div class="container p-3">
                <h4><b>결제 정보</b></h4>
            </div>

            <!-- 할인률 적용된 총 가격 -->
            <div class="container">
                <div class="row">
                    <div class="p-2">
                        <c:set var="totalBuyPrice" value="0"/>
                        <c:set var="discountRate" value="${sessionScope.discount_rate}"/>
                        <fn:forEach items="${requestScope.orderItemDtoList}" var="orderItemDto">
                            <c:set var="productSum" value="${orderItemDto.itemQuantity * orderItemDto.itemPrice}"/>
                            <c:set var="totalBuyPrice" value="${totalBuyPrice + productSum}"/>
                        </fn:forEach>
                        <c:set var="discountedTotalPrice" value="${(totalBuyPrice * (1-discountRate))}"/>
                        <fmt:formatNumber var="intDiscountedTotalPrice" type="number" maxFractionDigits="0" value="${discountedTotalPrice}"/>
                        <table>
                            <thead>
                            <tr>
                                <th>총 결제 금액</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    ${intDiscountedTotalPrice}원
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div id="payment">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="continue__btn update__btn">
                        <button name="callKakaoPay">결제 하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


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
