<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
        crossorigin="anonymous"></script>
<%--
<script>
    $(document).ready(function() {
        $("⁠OrderButton").on("click", function() {
            let data = [{
                itemId : 2,
                cartId : 1,
                itemName : '생수',
                itemQuantity :3,
                itemPrice :123 ,
            },{
                itemId : 23,
                cartId : 23,
                itemName : '생수2',
                itemQuantity :33,
                itemPrice :1232 ,
            }];

            let jsonData = JSON.stringify(data);
            console.log(jsonData);

            $.ajax({
                url: "/order",
                type: "POST",
                data: {jsonData: jsonData},
                async: false,
                success: function(response) {},
                error: function(jqXHR, textStatus, errorThrown) {}
            });
        });
    });
</script>
--%>


<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데 ON : 주문 내역</title>

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
    <!-- Header 끝 -->

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
                                                            <td>${orderSetDto.representative}</td>
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
                                                    <a href="${pageContext.request.contextPath}/orderDetail?orderSetId=${orderSetDto.orderSetId}">Order
                                                        Detail</a>
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


