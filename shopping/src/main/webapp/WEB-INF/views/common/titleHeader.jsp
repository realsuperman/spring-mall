
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="pathUri" value="${pageContext.request.requestURL.substring(pageContext.request.requestURL.lastIndexOf('/') + 1)}" />

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">

                        <c:choose>
                            <c:when test="${pathUri == 'cart.jsp' || pathUri == 'cartDetail.jsp' }" >
                                <h4>Shopping Cart</h4>
                                <div class="breadcrumb__links">
                                    <a href="/">Home</a>
                                    <span>Shopping Cart</span>
                                </div>
                            </c:when>
                            <c:when test="${pathUri == 'order.jsp'}" >
                                <h4>Order</h4>
                                <div class="breadcrumb__links">
                                    <a href="/">Home</a>
                                    <span>Order</span>
                                </div>
                            </c:when>
                            <c:when test="${pathUri == 'orderCancel.jsp'}" >
                                <h4>OrderCancel</h4>
                                <div class="breadcrumb__links">
                                    <a href="/">Home</a>
                                    <a href="/orderSetList">My Order</a>
                                    <span>OrderCancel</span>
                                </div>
                            </c:when>
                            <c:when test="${pathUri == 'item.jsp' || pathUri == 'itemDetail.jsp'}" >
                                <h4>Shopping</h4>
                                <div class="breadcrumb__links">
                                    <a href="/">Home</a>
                                    <span>Shopping</span>
                                </div>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
</body>
</html>
