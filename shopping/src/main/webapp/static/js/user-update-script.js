function userInfoUpdate() {

    let UpdateUserRequest = {
        updatePhoneNumber: $('#phone_number').val(),
        updateAddress: $('#address').val(),
        updateAddressDetail: $('#address_detail').val()
    };

    $.ajax({
        url : "/user/info",
        type : "PATCH",
        data : JSON.stringify(UpdateUserRequest),
        contentType: "application/json;charset=UTF-8",
        success: function (response) {
            window.location.href = "/user/my-page"
        },
        error: function (jqXHR) {
            if (jqXHR.status === 400) {
                alert(jqXHR.responseText);
            } else {
                alert("사용자 정보 수정에 실패하였습니다.")
            }
        }
    });
}

function userPassUpdate() {

    let UpdatePasswordRequest = {
        originalPassword: $('#original_password').val(),
        updatePassword: $('#update_password').val(),
    };

    $.ajax({
        url:"/user/pass",
        type: "PATCH",
        data : JSON.stringify(UpdatePasswordRequest),
        contentType: "application/json;charset=UTF-8",
        success: function (response) {
            window.location.href = "/user/my-page"
        },
        error: function (jqXHR, error) {
            if (jqXHR.status === 400) {
                alert(jqXHR.responseText);
            } else {
                alert("사용자 정보 수정에 실패하였습니다.");
            }
        }
    })

}

$(".user-update-form").submit(function(event) {
    event.preventDefault();
    userInfoUpdate()
})

$(".pass-update-form").submit(function(event) {
    event.preventDefault();
    userPassUpdate();
})

