$(function() {
    $(".check-box").change(function() {
        let cur = $(this).data("id");
        let checkbox = ".check-box-" + cur;
        if(!$(checkbox).is(":checked")) {
            console.log("excluded: ", cur); //체크가 안된 itemId를 감지
            $.ajax({
                url: "/sm/c/api/v1",
                type: "POST",
                data: JSON.stringify({ "excludedItemId" : cur}),
                contentType: "application/json",
                success: function(result) {
                    $("#std-parents").html(result);
                },
                error: function(xhr, err, status) {
                    console.log(xhr.responseText);
                    alert(err + "이(가) 발생했습니다: " + status);
                }
            });
        }

    });
});