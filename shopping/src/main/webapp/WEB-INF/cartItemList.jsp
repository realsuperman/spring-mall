<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="col-lg-8">
    <div class="shopping__cart__table">
        <table>
            <thead>
                <tr>
                    <th></th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th style="text-align:right;padding-right:40px;">Total</th>
                </tr>
            </thead>
            <tbody class="std-parents">
                <c:choose>
                    <c:when test="${empty cartItemsAll}">
                        <tr>
                            <td class="product__cart__item">
                                <i class="fa-solid fa-minus"></i>
                            </td>
                            <td class="quantity__item">
                                <i class="fa-solid fa-minus"></i>
                            </td>
                            <td class="cart__price subTotal-price-${status.index}" data-idx="${status.index}">
                                <i class="fa-solid fa-minus"></i>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${checkedList}" var="cartItem" begin="0" end="${checkedList.size()}" varStatus="status">
                                <tr class="check-item-list row-id" data-id="${cartItem.cartId}">
                                    <td>
                                        <input type="checkbox" name="check-${cartItem.itemId}" data-item="${cartItem.itemId}" class="check-item mx-3 check-${cartItem.itemId} row-item" checked/>
                                    </td>
                                    <td class="product__cart__item">
                                        <div class="product__cart__item__pic">
                                            <img src="${cartItem.itemImagePath}" width="90px" height="90px" alt="">
                                        </div>
                                        <div class="product__cart__item__text">
                                            <h6 class="check-item-list sec-name">${cartItem.itemName}</h6>
                                            <h5 class="cartItem-price-${status.index}"><i class="fa-solid fa-won-sign"></i>  <fmt:formatNumber value="${cartItem.itemPrice}" /></h5>
                                            <input type="hidden" value="${cartItem.itemPrice - (cartItem.itemPrice * discount_rate)}" class="check-item-list each-discounted" />
                                        </div>
                                    </td>
                                    <td class="quantity__item">
                                        <div class="quantity d-flex flex-row">
                                            <i class="fa-solid fa-chevron-left left-arrow-${status.index} left-arrow" data-idx="${status.index}" style="color:gray;padding-top:5px;"></i>
                                            <input type="text" value="${cartItem.itemQuantity}" class="count-${status.index} mx-3 check-item-list input-val" data-idx="${status.index}" />
                                            <i class="fa-solid fa-chevron-right right-arrow-${status.index} right-arrow" data-idx="${status.index}" style="color:gray;padding-top:5px;"></i>
                                        </div>
                                    </td>
                                    <td class="cart__price subTotal-price-${status.index}" data-idx="${status.index}">
                                        <div class="w-75" style="text-align:right;">
                                            <div><fmt:formatNumber value="${cartItem.totalPrice}" />원</div>
                                        </div>
                                    </td>
                                    <td class="cart__close"><i class="fa fa-close btn-close-${status.index} check-item-list btn-close" data-item="${cartItem.itemId}"></i></td>
                                </tr>
                        </c:forEach>
                        <c:forEach items="${uncheckedList}" var="cartItem" begin="0" end="${uncheckedList.size()}" varStatus="status">
                            <tr class="row-id" data-id="${cartItem.cartId}">
                                <td>
                                    <input type="checkbox" name="check-${cartItem.itemId}" data-item="${cartItem.itemId}" class="check-item mx-3 check-${cartItem.itemId} row-item" />
                                </td>
                                <td class="product__cart__item">
                                    <div class="product__cart__item__pic">
                                        <img src="${cartItem.itemImagePath}" width="90px" height="90px" alt="">
                                    </div>
                                    <div class="product__cart__item__text">
                                        <h6 class="sec-name">${cartItem.itemName}</h6>
                                        <h5 class="cartItem-price-${status.index}"><i class="fa-solid fa-won-sign"></i>  <fmt:formatNumber value="${cartItem.itemPrice}" /></h5>
                                        <input type="hidden" value="${cartItem.itemPrice - (cartItem.itemPrice * discount_rate)}" class="each-discounted" />
                                    </div>
                                </td>
                                <td class="quantity__item">
                                    <div class="quantity d-flex flex-row">
                                        <i class="fa-solid fa-chevron-left left-arrow-${status.index} left-arrow" data-idx="${status.index}" style="color:gray;padding-top:5px;"></i>
                                        <input type="text" value="${cartItem.itemQuantity}" class="count-${status.index} mx-3 input-val" data-idx="${status.index}" />
                                        <i class="fa-solid fa-chevron-right right-arrow-${status.index} right-arrow" data-idx="${status.index}" style="color:gray;padding-top:5px;"></i>
                                    </div>
                                </td>
                                <td class="cart__price subTotal-price-${status.index}" data-idx="${status.index}">
                                    <div class="w-75" style="text-align:right;">
                                        <div><fmt:formatNumber value="${cartItem.totalPrice}" />원</div>
                                    </div>
                                </td>
                                <td class="cart__close"><i class="fa fa-close btn-close-${status.index} btn-close" data-item="${cartItem.itemId}"></i></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <input type="hidden" value="${pageable.getCurPage()}" id="pager" />
    <input type="hidden" value="${pageable.getBlockStartNum()}" id="pager-start-block" />
    <input type="hidden" value="${pageable.getBlockLastNum()}" id="pager-last-block" />
    <input type="hidden" value="${pageable.getPageLastCartItem()}" id="pager-last-item" />
    <input type="hidden" value="${pageable.getPageStartCartItem()}" id="pager-start-item" />
    <div class="container d-flex justify-content-center">
        <div>
            <ul class="d-flex flex-row">
                <li style="list-style: none;"><a href="#" class="btn btn-light page-prev">Prev</a></li>
                <c:choose>
                    <c:when test="${pageable.getLastPageNum() <= pageable.getBlockLastNum()}">
                        <c:set var="endNum" value="${pageable.getLastPageNum()}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="endNum" value="${pageable.getBlockLastNum()}" />
                    </c:otherwise>
                </c:choose>
                <c:forEach var="i" begin="${pageable.getBlockStartNum()}" end="${endNum}">
                    <c:choose>
                        <c:when test="${pageable.getCurPage() == i}">
                            <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light page-cur-${i} page-cur page-active">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li style="list-style: none;" class="mx-1"><a href="#" class="btn btn-light page-cur">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <li style="list-style: none;"><a href="#" class="btn btn-light page-next">Next</a></li>
            </ul>
        </div>
    </div>
    <div class="row my-3">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <div class="continue__btn">
                <a href="/home">Continue Shopping</a>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-4">
    <div class="cart__total">
        <h6>Cart total</h6>
        <ul>
            <c:set var="totalPrice" value="0" />
            <c:set var="sumDiscount" value="0" />
            <c:forEach items="${cartItemsAll}" var="cartItem" varStatus="status">
                <c:forEach items="${checkedIdSet}" var="checkedId">
                    <c:choose>
                        <c:when test="${cartItem.itemId == checkedId}">
                            <c:set var="sumDiscount" value="${sumDiscount + (cartItem.totalPrice * discount_rate)}" />
                            <c:set var="discountedPrice" value="${cartItem.totalPrice - (cartItem.totalPrice * discount_rate)}" />
                            <li>${cartItem.itemName} <span><i class="fa-solid fa-won-sign"></i>&nbsp;<span class="summary-subTotal-${status.index} summary-subTotal" data-idx="${status.index}"><fmt:formatNumber value="${discountedPrice}" pattern="#,##0" /></span></span></li>
                            <c:set var="totalPrice" value="${totalPrice + discountedPrice}" />
                        </c:when>
                    </c:choose>
                </c:forEach>
            </c:forEach>
            <li>Discount <span>${grade}(&nbsp;<fmt:formatNumber value="${discount_rate * 100}" pattern="0.0" />%<i class="fa-solid fa-caret-down"></i>&nbsp;)</span></li>
            <li>Discount Total <span style="color:black;">- <i class="fa-solid fa-won-sign"></i>&nbsp;<fmt:formatNumber value="${sumDiscount}" pattern="#,##0" /></span></li>
            <li><B>Total</B> <span><i class="fa-solid fa-won-sign"></i>&nbsp;<span id="sum-price"><fmt:formatNumber value="${totalPrice}" pattern="#,##0" /></span></span></li>
        </ul>
        <form id="form-order" name="frm" action="/order" method="post">
            <input type="hidden" name="orderItemDtoList" class="input-hidden"/>
            <button type="submit" class="primary-btn btn-order w-100">주문하기</button>
        </form>
    </div>
</div>

<!-- Js Plugins -->
<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>
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

let errMsg = $("#err-msg").val();
console.log("errMsg: ", errMsg);

//if (params.msg) {
   // successModal(params.msg);
//}

if (errMsg) {
    console.log("errMsg call");
    errorModal(errMsg);
    console.log("errMsg call__end");
}
</script>
<script src="../static/js_test/dynamic.js"></script>