<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode( {
            oncomplete: function( data ) {
                callbackDaum(data);
            }
        } ).open();
    }

    function executeAjax(url, type, async, callback){
        $.ajax({
            url:url, // url
            type:type, // type
            async: async, // async
            success: function(result) {
                if (callback) {
                    callback(result); // callback이 존재할 경우에만 실행
                }
            }
        });
    }

    function callKakaoPay(form){
        $.ajax({
            url: "/kakao",
            type: "POST",
            data : form,
            async: false,
            success: function(result) {
                let parsedObject = JSON.parse(JSON.stringify(result));
                // sessionStorage.setItem('username', 'exampleUser');
                // TODO 이 시점에서 세션에 값을 로직을 추가하기
                // tid = parsedObject.tid;
                sessionStorage.setItem("tid", parsedObject.tid);
                // partner_order_id = form.partnerOrderId;
                sessionStorage.setItem("partner_order_id", form.partnerOrderId);
                // partner_user_id = form.partnerUserId;
                sessionStorage.setItem("partner_user_id", form.partnerUserId);
                // cancel_amount
                sessionStorage.setItem("cancel_amount", form.totalAmount);
                // cancel_tax_free_amount
                sessionStorage.setItem("cancel_tax_free_amount", form.taxFreeAmount);
                // orderItemDtoList
                sessionStorage.setItem("orderItemDtoList", form.orderItemDtoList);
                // orderInfoDto
                sessionStorage.setItem("orderInfoDto", form.orderInfoDto);

                window.open(parsedObject.next_redirect_pc_url, "myPopup", "width=600,height=600");
            },
            error: function(error) { // 에러메시지 처리
                alert(error.responseText);
            }
        });
    }


</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>