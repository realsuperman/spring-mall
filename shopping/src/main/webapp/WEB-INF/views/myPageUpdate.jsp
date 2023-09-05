<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>롯데온 : 마이페이지</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- 사용자 프로필 Css Styles -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA=" crossorigin="anonymous" />
    <link rel="stylesheet" href="../../static/css/profile.css" type="text/css">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../static/css_test/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../static/css_test/style.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
          integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        .left-arrow:hover, .right-arrow:hover {
            cursor: pointer;
        }
        .input-val {
            width: 45px;
            border: 1px #EEEEEE solid;
            border-radius: 5px;
            text-align: right;
            padding: 0 6px;
        }
    </style>
</head>

<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Header Section -->
<jsp:include page="common/header.jsp"></jsp:include>
<div style="position: relative; z-index: 1;     margin-top: 44px;">
    <jsp:include page="common/mypageHeader.jsp"></jsp:include>


<%-- 사용자 정보 조회 --%>
<!-- Form START -->
<form class="user-update-form">
    <div class="row2">
        <!-- Contact detail -->
        <div class="col-xxl-8 mb-5 mb-xxl-0">
            <div class="bg-secondary-soft px-4 py-5 rounded">
                <div class="row1">
                    <h4 class="mb-4 mt-0">사용자 정보 수정</h4>
                    <!-- Name -->
                        <div class="col-md-6">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control" name="username" id="username" value=" ${login_user.userName}" disabled>
                        </div>
                        <!-- Email -->
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="userEmail" id="email" value="${login_user.userEmail}" readonly>
                        </div>
                        <!-- Password -->
                        <div class="col-md-6">
                            <label for="password" class="form-label">Password</label>

                            <div class="inline-elements" style="display: flex; align-items: center; /* 세로 중앙 정렬 */">
                                <input type="password" class="form-control" name="password" id="password" value="1234567890"; style="margin-right: 22px;" disabled>
                                <button type="button" class="btn btn-primary" data-mdb-toggle="modal" id="my_btn" data-mdb-target="#exampleModal" style="width: 200px; border-radius: 30px; background-color:#f95959; color: #FFFFFF; border: none; ">
                                    비밀번호 변경
                                </button>
                            </div>
                        </div>

                        <!-- Phone number -->
                        <div class="col-md-6">
                            <label class="form-label">Phone Number</label>
                            <input type="text" class="form-control" name="updatePhoneNumber" id="phone_number" value="${login_user.phoneNumber}">
                        </div>
                        <!-- Address -->
                        <div class="col-md-6">
                            <label class="form-label">Address</label>
                            <input type="text" class="form-control" name="updateAddress" id="address" value="${login_user.address}">
                        </div>
                        <!-- Details Address -->
                        <div class="col-md-6">
                            <label class="form-label">Details Address</label>
                            <input type="text" class="form-control" name="updateAddressDetail" id="address_detail">
                        </div>

                        <div class="col-md-6">
                            <input class="form-control" type="submit" value="입력" style="width: 150px; border-radius: 30px; background-color:#f95959; color: #FFFFFF; border: none;">
                        </div>
                </div> <!-- Row END -->
            </div>
        </div>
    </div>
</form>
</div>



<!-- 비밀번호 변경 Modal -->
<div class="modal top fade" id="myModal" tabindex="-1"  role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 300px;">
        <div class="modal-content text-center">
            <div class="modal-header h5 text-white bg1-primary justify-content-center1" style="background-color:#f95959;" >
                비밀번호 변경
            </div>
            <div class="modal-body px-5">
                <p class="py-2">
                    기존 비밀번호와 변경할 비밀번호를 입력하세요.
                </p>
                <form class="pass-update-form">

                    <div class="form-group">
                        <label for="original_password">기존 비밀번호</label>
                        <input type="password" name="originalPassword" id="original_password" class="original_pass">
                        <span class="error"></span>
                    </div>

                    <div class="form-group">
                        <label for="update_password">변경할 비밀번호</label>
                        <input type="password" name="updatePassword" id="update_password" class="pass">
                        <span class="error"></span>
                    </div>

                    <div class="form-group">
                        <label for="update_passwordCon">비밀번호 확인</label>
                        <input type="password" name="updatePasswordCon" id="update_passwordCon" class="passConfirm">
                        <span class="error"></span>
                    </div>

                    <div class="CTA">
                        <input type="submit" value="변경" style="width: 150px; border-radius: 30px; background-color:#f95959; color: #FFFFFF; border: none;">
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>


<!-- 에러 메시지 alert -->
<div id="errorSection">
    <%@ include file="common/errorMsgAlert.jsp" %>
</div>

<%-- 유저 관련 script --%>
<script  src="../../static/js/userscript.js"></script>
<script src="../../static/js/password-modal-script.js"></script>
<script src="../../static/js/address-modal-script.js"></script>
<script  src="../../static/js/user-update-script.js"

<%-- 다음 주소 api --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../../static/js/address-api.js"></script>

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


</body>

</html>


