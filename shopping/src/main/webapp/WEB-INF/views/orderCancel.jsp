<%@ page import="com.bit.shoppingmall.dto.OrderCancelDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
        crossorigin="anonymous"></script>

<style>
    .input-val {
        width: 45px;
        border: 1px #EEEEEE solid;
        border-radius: 5px;
        text-align: right;
        padding: 0 6px;
    }
</style>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <title>주문 취소</title>
</head>
<body>
<!-- Page Preloder -->
<div id="preloder" style="display: none;">
    <div class="loader" style="display: none;"></div>
</div>
<!-- 장바구니와 같은 View -->
<section class="shopping-cart spad">
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
                                <th></th>
                                <th>Item Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.orderCancelDtoList}" var="orderCancelDto"
                                       varStatus="state">
                                <tr class="check-item-list" data-id="${orderCancelDto.itemId}">
                                    <td>
                                        <input type="checkbox" name="check-${orderCancelDto.itemId}"
                                               data-item="${orderCancelDto.itemId}" class="row-item">
                                    </td>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__text">${orderCancelDto.itemName}</div>
                                    </td>
                                    <!-- 취소할 갯수 지정 -->
                                    <td class="quantity__item">
                                        <div class="product__cart__item__text">
                                            <i class="fa-solid fa-chevron-left left-arrow-${state.index} left-arrow"
                                               data-idx="${state.index}" style="color:gray;padding-top:5px;"></i>
                                            <input type="text" value="${orderCancelDto.itemQuantity}"
                                                   class="count-${state.index} mx-3 input-val"
                                                   data-idx="${state.index}"/>
                                            <i class="fa-solid fa-chevron-right right-arrow-${state.index} right-arrow"
                                               data-idx="${state.index}" style="color:gray;padding-top:5px;"></i>
                                        </div>
                                    </td>
                                    <td class="product__cart__item__text">${orderCancelDto.buyPrice}</td>
                                    <td class="product__cart__item__text">${orderCancelDto.statusName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- 총 환불 금액 -->
                    <div class="container">
                        <div class="row">
                            <div class="p-2">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Total Cancel Price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td></td>
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
                                <button id="cancelOrder">Cancel Order</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>

<script>
    $(function () {
        let itemQuantityList = [];
        <c:forEach items="${requestScope.orderCancelDtoList}" var="orderCancelDto" varStatus="state">
        itemQuantityList.push(parseInt(${orderCancelDto.itemQuantity}));
        </c:forEach>

        // cancelOrder 버튼을 클릭했을 때
        $("#cancelOrder").click(function () {
            let orderCancelDtoList = [];

            // table을 순회하면서 orderCancelDto 생성해서 리스트에 append
            $(".check-item-list").each(function () {
                let isChecked = $(this).find(".row-item").prop("checked");

                if (isChecked) {
                    const itemId = $(this).data("id");
                    const itemName = $(this).find(".product__cart__item__text").text().split('\n')[0];
                    const itemQuantity = parseInt($(this).find(".input-val").val());
                    const buyPrice = parseInt($(this).find(".product__cart__item__text").eq(2).text());
                    const statusName = $(this).find(".product__cart__item__text").eq(3).text();

                    const orderCancelDto = {
                        itemId: itemId,
                        itemName: itemName,
                        itemQuantity: itemQuantity,
                        buyPrice: buyPrice,
                        statusName: statusName
                    };

                    orderCancelDtoList.push(orderCancelDto);
                }
            });

            <%Long orderSetId = (Long) request.getAttribute("orderSetId");%>
            let orderSetId = parseInt(<%= orderSetId %>);
            let jsonData = {
                "orderSetId" : orderSetId,
                "orderCancelDtoList" :orderCancelDtoList
            }

            // orderCancelDtoList가 비어있지 않다면
            if (orderCancelDtoList.length > 0) {
                $.ajax({
                    url: "/orderCancel",
                    type: "PUT",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(jsonData),
                    success: function () {
                        alert("주문 취소가 완료되었습니다");
                        window.location.href = "/"
                    },
                    error: function(request, status, error) {
                        alert(error);
                    }
                });
            } else {
                alert("취소할 주문을 고르세요");
            }
        });

        //수량 감소 버튼
        $(".left-arrow").click(function () {
            const index = $(this).data("idx");
            const inputField = $(".count-" + index);

            let currentValue = parseInt(inputField.val());
            if (currentValue > 1) {
                currentValue--;
                inputField.val(currentValue);
            }
        });

        //수량 증가 버튼
        $(".right-arrow").click(function () {
            const index = $(this).data("idx");
            const inputField = $(".count-" + index);

            let currentValue = parseInt(inputField.val());
            // 주문했던 갯수 이하로 제한
            if (currentValue < itemQuantityList[index]) {
                currentValue++;
                inputField.val(currentValue);
            }
        });
    });
</script>