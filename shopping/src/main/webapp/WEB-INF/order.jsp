<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/commonScript.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
    $(function () {
        let orderCode = "123";
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
        formData.taxFreeAmount = formData.totalAmount;

        $("button[name='callKakaoPay']").on("click", function () {
            console.log(formData);
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
    <title>롯데 ON : 주문</title>

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
    <jsp:include page="common/titleHeader.jsp"></jsp:include>
    <!-- Header 끝 -->

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
                            <fn:forEach items="${requestScope.orderItemDtoList}" var="orderItemDto">
                                <c:set var="productSum" value="${orderItemDto.itemQuantity * orderItemDto.itemPrice}"/>
                                <c:set var="totalBuyPrice" value="${totalBuyPrice + productSum}"/>
                            </fn:forEach>
                            <table>
                                <thead>
                                <tr>
                                    <th>총 결제 금액</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        ${totalBuyPrice}원
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
