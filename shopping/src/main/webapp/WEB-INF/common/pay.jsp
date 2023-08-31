<%--
  Created by IntelliJ IDEA.
  User: seong
  Date: 2023-08-24
  Time: 오전 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./commonScript.jsp" %>
<script>
    $(document).ready(function() {
        let queryString = window.location.search;
        let params = new URLSearchParams(queryString);
        if(params.get('mode')==='fail'){ // 결제 취소 or 실패
            alert("결제가 정상적으로 처리되지 않았습니다.");
            window.close();
            return;
        }

        let kakaoPayVO = JSON.stringify({
            cid: "TC0ONETIME", // TODO : 하드 코딩 수정
            tid: sessionStorage.getItem("tid"),
            partnerOrderId: sessionStorage.getItem("partner_order_id"),
            partnerUserId: sessionStorage.getItem("partner_user_id"),
            pgToken : params.get('pg_token'),
            cancelAmount: sessionStorage.getItem("cancel_amount"),
            cancelTaxFreeAmount: sessionStorage.getItem("cancel_tax_free_amount")
        });

        console.log(kakaoPayVO);

        $.ajax({
            url: "/payment",
            type: "POST",
            data : {
                kakaoPayVO: kakaoPayVO,
                orderInfoDto: sessionStorage.getItem("orderInfoDto"),
                orderItemDtoList: sessionStorage.getItem("orderItemDtoList")
            },
            async: false,
            success: function(result) {
                sessionStorage.clear();
                alert("결제 성공");
                window.opener.location.href = '/home'
            },
            error: function(error) { // 재고가 부족합니다 or 결제가 실패했습니다
                alert(error.responseText);
            }
        });
        window.close();
    });
</script>