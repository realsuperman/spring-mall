$(function() {
    let count = $(".input-val").val();
    let curPageNumber = $("#pager").val();
    let blockStart = $("#pager-start-block").val();
    let blockLast = $("#pager-last-block").val();
    let itemLast = $("#pager-last-item").val();
    let itemStart = $("#pager-start-item").val();

    let discountedArray = [];
    $(".summary-subTotal").each(function() {
        let discounted = $(this).text();
        discounted = discounted.replace(/,/g, '');
        discountedArray.push(discounted);
    });

    //checkbox 토글
    $(".row-item").change(function() {
        let rowItemId = $(this).data("item");
        let rowSelector = ".check-" + rowItemId;
        let url = "/checked";
        if($(rowSelector).is(":checked")) {
            $.LoadingOverlay("show");
            $.ajax({
                url:"/cart-ajax/checked",
                type: "POST",
                data: JSON.stringify({"checkedId": rowItemId, "url": url}),
                contentType: "application/json",
                success: function(result) {
                    $.ajax({
                        url: "cart-ajax",
                        type: "GET",
                        success: function(result) {
                            $('.replace-parents').html(result);
                        },
                        error: function(xhr, err, status) {
                            console.log(xhr.responseText);
                            alert(err + "이(가) 발생했습니다: " + status);
                        }
                    });
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err + "이(가) 발생했습니다: " + status);
                }
            });
            $.LoadingOverlay("hide");
        } else {
            url = "/unchecked";
            $.LoadingOverlay("show");
                $.ajax({
                    url:"/cart-ajax/unchecked",
                    type: "POST",
                    data: JSON.stringify({"uncheckedId": rowItemId, "url": url}),
                    contentType: "application/json",
                    success: function(result) {
                        $.ajax({
                            url: "cart-ajax",
                            type: "GET",
                            success: function(result) {
                                $('.replace-parents').html(result);
                            },
                            error: function(xhr, err, status) {
                                console.log(xhr.responseText);
                                alert(err + "이(가) 발생했습니다: " + status);
                            }
                        });
                    },
                    error: function(xhr, err, status) {
                        console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                    }
                });
                $.LoadingOverlay("hide");
        }
    });

    //수량 입력 버튼
    $(".input-val").keypress(function(event) {
        if (event.which === 13) { // Enter 키의 key code는 13입니다.
           let idxVal = $(this).data("idx");
           let eachPrice = ".cartItem-price-" + idxVal;
           let countSelector = ".count-" + idxVal;
           let priceSelector = ".subTotal-price-" + idxVal;
           let summarySelector = ".summary-subTotal-" + idxVal;
           let closeSelector = ".btn-close-" + idxVal;

           let itemId = $(closeSelector).data("item");
           let preSubTotal = $(priceSelector).text();
           let preSum = $("#sum-price").text();
           let countVal = $(this).val();
           let curCnt = $(countSelector).val();

           let subTotalPrice = parseInt($(eachPrice).text()) * countVal;
           $(priceSelector).text(subTotalPrice.toLocaleString() + "원");
           $(summarySelector).text(subTotalPrice.toLocaleString());

           let withoutComma = $("#sum-price").text().replace(/,/g, '');
           preSum = preSum.replace(/,/g, '');
           preSubTotal = preSubTotal.replace("원", '');
           preSubTotal = preSubTotal.replace(/,/g, '');
           let cur = parseInt(preSum) - parseInt(preSubTotal) + parseInt(subTotalPrice);

           $("#sum-price").text(cur.toLocaleString());
           $.LoadingOverlay("show");
           $.ajax({
               url: "cart",
               type: "PUT",
               data: JSON.stringify({"itemId": itemId, "cnt": curCnt}),
               contentType: "application/json",
               success: function(result) {
                   $.ajax({
                        url: "cart-ajax",
                        type: "GET",
                        success: function(result) {
                            $('.replace-parents').html(result);
                        },
                        error: function(xhr, err, status) {
                            console.log(xhr.responseText);
                            alert(err + "이(가) 발생했습니다: " + status);
                        }
                   });
               },
               error: function(xhr, err, status) {
                   console.log(xhr.responseText);
                   alert(err + "이(가) 발생했습니다: " + status);
               }
           });
           $.LoadingOverlay("hide");
        }
    });

    //수량 감소 버튼
    $(".left-arrow").click(function() {
        let idxVal = $(this).data("idx");
        let countSelector = ".count-" + idxVal
        let summarySelector = ".summary-subTotal-" + idxVal;
        let eachPrice = ".cartItem-price-" + idxVal;
        let priceSelector = ".subTotal-price-" + idxVal;
        let closeSelector = ".btn-close-" + idxVal;
        let itemId = $(closeSelector).data("item");

        count = $(countSelector).val();
        if(count == 1) {
            $(countSelector).val(1);
        } else {
            count--;
            $(countSelector).val(count);
        }

        let subTotalPrice = parseInt($(eachPrice).text()) * count;
        let curCnt = $(countSelector).val();

        $(priceSelector).text(subTotalPrice.toLocaleString() + "원");
        $(summarySelector).text(subTotalPrice.toLocaleString());
        let withoutComma = $("#sum-price").text().replace(/,/g, '');
        let cur = parseInt(withoutComma) - parseInt($(eachPrice).text());

        $("#sum-price").text(cur.toLocaleString());
        $.LoadingOverlay("show");
        $.ajax({
            url: "cart",
            type: "PUT",
            data: JSON.stringify({"itemId": itemId, "cnt": curCnt}),
            contentType: "application/json",
            success: function(result) {
                $.ajax({
                     url: "cart-ajax",
                     type: "GET",
                     success: function(result) {
                         $('.replace-parents').html(result);
                     },
                     error: function(xhr, err, status) {
                         console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                     }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
        $.LoadingOverlay("hide");
    });

    //수량 증가 버튼
    $(".right-arrow").click(function() {
        let idxVal = $(this).data("idx");
        let countSelector = ".count-" + idxVal;
        let summarySelector = ".summary-subTotal-" + idxVal;
        let eachPrice = ".cartItem-price-" + idxVal;
        let priceSelector = ".subTotal-price-" + idxVal;
        let closeSelector = ".btn-close-" + idxVal;
        let itemId = $(closeSelector).data("item");

        count = $(countSelector).val();
        count++;
        $(countSelector).val(count);
        let curCnt = $(countSelector).val();
        let subTotalPrice = parseInt($(eachPrice).text()) * count;

        $(priceSelector).text(subTotalPrice.toLocaleString() + "원");
        $(summarySelector).text(subTotalPrice.toLocaleString());
        let withoutComma = $("#sum-price").text().replace(/,/g, '');
        let cur = parseInt(withoutComma) + parseInt($(eachPrice).text());

        $("#sum-price").text(cur.toLocaleString());
        $.LoadingOverlay("show");
        $.ajax({
            url: "cart",
            type: "PUT",
            data: JSON.stringify({"itemId": itemId, "cnt": curCnt}),
            contentType: "application/json",
            success: function(result) {
                console.log("result: ", result);
                $.ajax({
                     url: "cart-ajax",
                     type: "GET",
                     success: function(result) {
                         $('.replace-parents').html(result);
                     },
                     error: function(xhr, err, status) {
                         console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                     }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
        $.LoadingOverlay("hide");
    });

    //cart item 삭제
    $(".btn-close").on("click", function() {
        let itemId = $(this).data("item");
        $.LoadingOverlay("show");
        $.ajax({
            url: "cart",
            type: "DELETE",
            data: JSON.stringify({"itemId": itemId}),
            contentType: "application/json",
            success: function(result) {
                $.ajax({
                    url: "cart-ajax",
                    type: "GET",
                    success: function(result) {
                        $('.replace-parents').html(result);
                    },
                    error: function(xhr, err, status) {
                       console.log(xhr.responseText);
                       alert(err + "이(가) 발생했습니다: " + status);
                    }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
        $.LoadingOverlay("hide");
    });

    //페이지네이션
    $(".page-prev").on("click", function(event) {
        event.preventDefault();

        let curPageNum = parseInt($("#pager").val());
        let prevPageNum = curPageNum - 1;
        let nextPageNum = curPageNum + 1;
        let pageStartCartItem = parseInt($("#pager-start-item").val())-5;
        let pageLastCartItem = parseInt($("#pager-last-item").val())-5;

        $.ajax({
            url: "cart-ajax",
            type: "POST",
            data: JSON.stringify({"curPageNum": curPageNum, "prevPageNum": prevPageNum, "nextPageNum": nextPageNum, "pageStartCartItem": pageStartCartItem, "pageLastCartItem": pageLastCartItem, "flag": 0, "url": ""}),
            contentType: "application/json",
            success: function(result) {
                $.ajax({
                    url: "cart-ajax",
                    type: "GET",
                    success: function(result) {
                        $('.replace-parents').html(result);
                    },
                    error: function(xhr, err, status) {
                        console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                    }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
    });
    $(".page-next").on("click", function(event) {
        console.log("next click");
        event.preventDefault();

        let curPageNum = parseInt($("#pager").val());
        console.log("curPageNum: ", curPageNum);

        let prevPageNum = curPageNum - 1;
        console.log("prevPageNum: ", prevPageNum);

        let nextPageNum = curPageNum + 1;
        console.log("nextPageNum: ", nextPageNum);

        let pageStartCartItem = parseInt($("#pager-start-item").val())+5;
        let pageLastCartItem = parseInt($("#pager-last-item").val())+5;
        $.ajax({
            url: "cart-ajax",
            type: "POST",
            data: JSON.stringify({"curPageNum": curPageNum, "prevPageNum": prevPageNum, "nextPageNum": nextPageNum, "pageStartCartItem": pageStartCartItem, "pageLastCartItem": pageLastCartItem, "flag": 1, "url": ""}),
            contentType: "application/json",
            success: function(result) {
                $.ajax({
                    url: "cart-ajax",
                    type: "GET",
                    success: function(result) {
                        console.log("cart-ajax cartItemList:"+result);
                        $('.replace-parents').html(result);
                    },
                    error: function(xhr, err, status) {
                        console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                    }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
    });
    $(".page-cur").on("click", function() {
        let num = $(this).text();
        console.log("num: ", num);
        let pageNumSelector = ".page-cur-" + num;
        let curPageNum = num;
        let prevPageNum = parseInt(num)-1;
        let nextPageNum = parseInt(num)+1;
        let pageStartCartItem = (num-1)*5;
        let pageLastCartItem = num*5;
        $.ajax({
            url: "cart-ajax",
            type: "POST",
            data: JSON.stringify({"curPageNum": curPageNum, "prevPageNum": prevPageNum, "nextPageNum": nextPageNum, "pageStartCartItem": pageStartCartItem, "pageLastCartItem": pageLastCartItem, "flag": 2, "url": ""}),
            contentType: "application/json",
            success: function(result) {
                $.ajax({
                    url: "cart-ajax",
                    type: "GET",
                    success: function(result) {
                        console.log("cart-ajax cartItemList:"+result);
                        $('.replace-parents').html(result);
                    },
                    error: function(xhr, err, status) {
                        console.log(xhr.responseText);
                        alert(err + "이(가) 발생했습니다: " + status);
                    }
                });
            },
            error: function(xhr, err, status) {
                console.log(xhr.responseText);
                alert(err + "이(가) 발생했습니다: " + status);
            }
        });
    });


    //주문하기 버튼 클릭
    $("#form-order").submit( function(event) {
        let cartIdArray = [];
        let itemIdArray = [];
        let itemNameArray = [];
        let itemQuantityArray = [];
        let eachDiscountedArray = [];

        $(".check-item-list.row-id").each(function() {
            console.log("row--id");
            let eachCartId = $(this).data("id");
            cartIdArray.push(eachCartId);
        });

        $(".check-item-list.btn-close").each(function() {
            let eachitemId = $(this).data("item");
            itemIdArray.push(eachitemId);
        });

        $(".check-item-list.sec-name").each(function() {
            let eachItemName = $(this).text();
            itemNameArray.push(eachItemName);
        });

        $(".check-item-list.input-val").each(function() {
            let eachItemQuantity = $(this).val();
            itemQuantityArray.push(eachItemQuantity);
        });

        $(".check-item-list.each-discounted").each(function() {
            let eachDiscounted = $(this).val();
            eachDiscounted = parseInt(eachDiscounted);
            eachDiscountedArray.push(eachDiscounted);
        });

        datas = []
        for(let i = 0; i < cartIdArray.length; i++) {
            let jsonFormat = {}
            jsonFormat["itemId"] = itemIdArray[i];
            jsonFormat["cartId"] = cartIdArray[i];
            jsonFormat["itemName"] = itemNameArray[i];
            jsonFormat["itemQuantity"] = itemQuantityArray[i];
            jsonFormat["itemPrice"] = eachDiscountedArray[i];
            datas.push(jsonFormat);
        }
        console.log("datas: ", datas);

        let jsonData = JSON.stringify(datas);
        $(".input-hidden").val(jsonData);
        let value = $(".input-hidden").val().trim();
        console.log("value: ", value);
        if($(".input-hidden").val().trim() == "[]") {
            alert("장바구니 상품을 1개 이상 선택해주세요.");
            return false;
        }
    });
});
