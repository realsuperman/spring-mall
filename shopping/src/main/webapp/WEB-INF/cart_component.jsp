<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:forEach items="${foundItemDtos}" var="cartItem">
    <tr>
        <c:choose>
            <c:when test="${cartItem.isExcluded == true}">
                <td>
                    <input type="checkbox" class="check-box check-box-${cartItem.itemId} mx-3" data-id="${cartItem.itemId}" />
                </td>
            </c:when>
            <c:otherwise>
                <td>
                    <input type="checkbox" class="check-box check-box-${cartItem.itemId} mx-3" data-id="${cartItem.itemId}" checked/>
                </td>
            </c:otherwise>
        </c:choose>
        <td class="product__cart__item">
            <div class="product__cart__item__pic">
                <img src="${cartItem.itemImgPaths}" alt="">
            </div>
            <div class="product__cart__item__text">
                <h6>${cartItem.itemName}</h6>
                <h5><i class="fa-solid fa-won-sign"></i>  <fmt:formatNumber value="${cartItem.itemPrice}" /></h5>
            </div>
        </td>
        <td class="quantity__item">
            <div class="quantity d-flex flex-row">
                <i class="btn-decrease fa-solid fa-chevron-left" style="color:gray;padding-top:5px;"></i>
                <input type="text" value="${cartItem.itemQuantity}" class="input-quantity mx-3" />
                <i class="btn-increase fa-solid fa-chevron-right" style="color:gray;padding-top:5px;"></i>
            </div>
        </td>
        <td class="cart__price">
            <div class="w-75" style="text-align:right;">
                <div><fmt:formatNumber value="${cartItem.subTotalPrice}" /></div>
            </div>
        </td>
        <td><i class="btn-delete fa fa-close"></i></td>
    </tr>
</c:forEach>