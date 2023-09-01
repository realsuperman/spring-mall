<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데 ON : 아이템</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

</head>

<body>
<c:set var = "images" value = "${fn:split(item.itemImagePath,';')}"/>
<c:set var = "downPrefix" value = "https://firebasestorage.googleapis.com/v0/b/shoppingmall-c6950.appspot.com/o/"/>
<c:set var = "downSuffix" value = "?alt=media"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

<jsp:include page="common/header.jsp"></jsp:include>
<div style="position: relative; z-index: 1;     margin-top: 44px;">
    <jsp:include page="common/titleHeader.jsp"></jsp:include>

<section class="shop spad">
    <section class="shopping-cart spad">
        <div class = "container">
            <div class="row">
                <div class="col-lg-12">
                    <div class ="shop__product__option">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="shop__product__option__left">
                                    <span>
                                        <c:forEach items = "${upperCategoryNames}" var = "upperCategory" varStatus="status">
                                            ${upperCategory}
                                            <c:if test ="${!status.last}"> > </c:if>
                                        </c:forEach>
                                    </span>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class = "row">
                <div class="col-lg-6">
                    <img src = "${downPrefix}${images[0]}${downSuffix}" />
                </div>
                <div class="col-lg-6">
                    <div class="cart__discount">
                        <h6 style="display:inline">상품명</h6> ${item.itemName}
                    </div>

                    <div class="cart__discount">
                        <h6 style="display:inline">가격</h6> <fmt:formatNumber value="${item.itemPrice}" /> 원
                    </div>


                        <div class="cart__discount">
                            <span>
                                <i class="fa-solid fa-square-minus fa-1x" id = "minus-icon" style="color:gray;padding-top:5px;"></i>
                                <input type="number" value="1" min = "1" max = "${cargoCnt}" id = "input-val"/>
                                <i class="fa-solid fa-square-plus fa-1x" id = "plus-icon" style="color:gray;padding-top:5px;"></i>
                            </span>
                            <c:if test = "${cargoCnt le 10}">
                                상품이 <h6 style="display:inline">${cargoCnt}개</h6> 남았습니다.
                            </c:if>
                        </div>


                    <form action="/cart" method = "POST" class="formButton">
                        <input type = "hidden" id = "putInCartDto" name = "putInCartDto">
                        <button type = "submit" id = "addCartButton" class= "primary-btn btn-order">장바구니 담기</button>
                    </form>


                    <form action="/order" method="POST" class="formButton" id ="buyNow">
                        <input type = "hidden" id= "orderItemDtoList" name="orderItemDtoList">
                        <button type="submit" id = "buyButton" class= "primary-btn btn-order">바로 구매하기</button>
                    </form>

                </div>
            </div>
            <div class = "row">
                <div class="col-lg-12">
                    <img src = "${downPrefix}${images[1]}${downSuffix}" />
                </div>
            </div>
            <div class = "row">
                <div class="col-lg-12">
                    <img src = "${downPrefix}${images[2]}${downSuffix}" />
                </div>
            </div>
            <div class = "row">
                <div class="col-lg-12">
                    <img src = "${downPrefix}${images[3]}${downSuffix}" />
                </div>
            </div>
            <div class = "row">
                <div class="col-lg-12">
                    <img src = "${downPrefix}${images[4]}${downSuffix}" />
                </div>
            </div>
            <div class = "row">
                <div class="col-lg-12">
                    <img src = "${downPrefix}${images[5]}${downSuffix}" />
                </div>
            </div>
        </div>

        <input type="hidden" id="cargoCnt" name="cargoCnt" value="${cargoCnt}">
        <input type="hidden" id="itemId" name="itemId" value="${item.itemId}">
        <input type="hidden" id="itemName" name="itemName" value="${item.itemName}">
        <input type="hidden" id="itemPrice" name="itemPrice" value="${item.itemPrice}">
        <input type="hidden" id="itemImagePath" name="itemImagePath" value="${downPrefix}${images[0]}${downSuffix}">
        <input type="hidden" id="loginToken" name ="loginToken" value="${not empty login_user}">

    </section>
</section>

<jsp:include page="common/footer.jsp"></jsp:include>
</div>

</body>
<style>
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    input{
        width: 38px;
    }

    .formButton{
        float:left;
    }

    #buyNow{
        margin-left:50px;
    }
</style>

<script th:inline="javascript">
toastr.options = {
    closeButton: false,
    debug: false,
    newestOnTop: false,
    progressBar: false,
    positionClass: "toast-top-right",
    preventDuplicates: false,
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

function successModal(msg) {
    toastr["success"](msg);
}

function errorModal(msg) {
    toastr["error"](msg);
}

const queryString = window.location.search;

// 쿼리 문자열을 파싱하여 객체로 변환
const queryParams = new URLSearchParams(queryString);

// 특정 쿼리 매개변수 값 가져오기
const sucMsg = queryParams.get('sucMsg');

console.log("sucMsg: ", sucMsg);

if (sucMsg != null) {
   successModal(sucMsg);
}

</script>
<script>
    let count = $("#input-val").val() * 1;
    let cargoCnt = document.getElementById("cargoCnt").value * 1;
    if(cargoCnt > 10){
        cargoCnt = 999;
    }
    let itemId = document.getElementById("itemId").value * 1;
    let itemName = document.getElementById("itemName").value;
    let itemPrice = document.getElementById("itemPrice").value * 1;
    let itemImagePath = document.getElementById("itemImagePath").value;
    let isLogined = document.getElementById("loginToken").value === "true";

    $("#input-val").keypress(function(event) {
        if (event.which === 13) {
            count = $("#input-val").val();
            if(count < 1){
                $("#input-val").val(1);
                count = 1;
            }else if(count > cargoCnt){
                $("#input-val").val(cargoCnt);
                count = cargoCnt;
            }
        }
    });

    $("#minus-icon").click(function() {
        count--;
        if(count < 1){
            $("#input-val").val(1);
            count = 1;
        }else{
            $("#input-val").val(count);
        }

    });

    $("#plus-icon").click(function() {
        count++;
        if(count > cargoCnt){
            $("#input-val").val(cargoCnt);
            count = cargoCnt;
        }else{
            $("#input-val").val(count);
        }
    });

    $(document).ready(function() {
        $("#buyButton").on("click", function(event) {
            if(isLogined){
                let data = [{
                    "itemId" : itemId,
                    "cartId" : 1,
                    "itemName" : itemName,
                    "itemQuantity" : count,
                    "itemPrice" : itemPrice,
                }];

                let jsonData = JSON.stringify(data);
                $("#orderItemDtoList").val(jsonData);
            }else{
                window.alert("바로 구매하기 기능은 로그인 후 이용하실 수 있습니다.");
                event.preventDefault();
            }
        });

        $("#addCartButton").on("click", function(event){
            console.log("click");
            if(isLogined){
                let data = {
                    "itemId" : itemId,
                    "itemName" : itemName,
                    "itemPrice" : itemPrice,
                    "itemQuantity" : count,
                    "itemImagePath" : itemImagePath,
                };
                let jsonData = JSON.stringify(data);
                console.log("jsonData: ", jsonData);
                $("#putInCartDto").val(jsonData);
            }else{
                window.alert("장바구니 기능은 로그인 후 이용하실 수 있습니다.");
                event.preventDefault();
            }
        })


    });

</script>
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
</html>