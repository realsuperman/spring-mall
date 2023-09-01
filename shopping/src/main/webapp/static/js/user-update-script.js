function userInfoUpdate() {

    let UpdateUserRequest = {
        updatePhoneNumber: $('#phone_number').val(),
        updateAddress: $('#address').val(),
        updateAddressDetail: $('#address_detail').val()
    };

    console.log(UpdateUserRequest);

    var xhr = new XMLHttpRequest();
    xhr.open("PATCH", "/user/info", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                window.location.href = "/user/my-page"

            } else {
                alert("사용자 정보 수정에 실패하였습니다.");
            }
        }
    };
    xhr.send(JSON.stringify(UpdateUserRequest));

}


function userPassUpdate() {

    let UpdatePasswordRequest = {
        originalPassword: $('#original_password').val(),
        updatePassword: $('#update_password').val(),
    };

    var xhr = new XMLHttpRequest();
    xhr.open("PATCH", "/user/pass", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                window.location.href = "/user/my-page"
            } else {
                alert("사용자 정보 수정에 실패하였습니다.");
            }
        }
    };
    xhr.send(JSON.stringify(UpdatePasswordRequest));

}

$(".user-update-form").submit(function(event) {
    console.log("업데이트 시도")
    event.preventDefault();
    userInfoUpdate()
})

$(".pass-update-form").submit(function(event) {
    console.log("비밀번호 업데이트 시도");
    event.preventDefault();
    userPassUpdate();
})

