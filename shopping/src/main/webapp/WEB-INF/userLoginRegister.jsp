
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>롯데 ON : 회원가입/로그인</title>
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'><link rel="stylesheet" href="./style.css">

  <!-- Css Styles -->
  <link rel="stylesheet" href="../../static/css/userstyle.css" type="text/css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <section id="formHolder">

    <div class="row">

      <!-- Brand Box -->
      <div class="col-sm-6 brand">
        <a href="#" class="logo">LOTTE ON <span>.</span></a>

        <div class="heading">
          <h2>LOTTE ON</h2>
          <p>Enjoy Shopping</p>
        </div>
      </div>


      <!-- Form Box -->
      <div class="col-sm-6 form">

        <!-- Login Form -->
        <div class="login form-peice">
          <form class="login-form" action="/user/login" method="post">
            <div class="form-group">
              <label for="loginemail">Email Adderss</label>
              <input type="email" name="userEmail" id="loginemail" required>
            </div>

            <div class="form-group">
              <label for="loginPassword">Password</label>
              <input type="password" name="password" id="loginPassword" required>
            </div>

            <div class="CTA">
              <input type="submit" value="Login">
              <a href="#" class="switch">계정 생성</a>
            </div>
          </form>
        </div><!-- End Login Form -->


        <!-- Signup Form -->
        <div class="signup form-peice switched">
          <form class="signup-form" action="/user/sign-up" method="post">

            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" name="userEmail" id="email" class="email">
              <span class="error"></span>
              <span class="error"><form:errors path="userEmail"/></span>
            </div>

            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" name="password" id="password" class="pass">
              <span class="error"></span>
              <span class="error"><form:errors path="password"/></span>
            </div>

            <div class="form-group">
              <label for="passwordCon">Confirm Password</label>
              <input type="password" name="passwordCon" id="passwordCon" class="passConfirm">
              <span class="error"></span>
            </div>

            <div class="form-group">
              <label for="name">Name</label>
              <input type="text" name="userName" id="name" class="name">
              <span class="error"></span>
              <span class="error"><form:errors path="userName"/></span>
            </div>

            <div class="form-group">
              <label for="phone">Phone Number</label>
              <input type="text" name="phoneNumber" id="phone">
              <span class="error"><form:errors path="phoneNumber"/></span>
            </div>

            <div class="form-group">
              <label for="address">Address</label>
              <input type="text" name="address" id="address" class="address">
              <input type="text" name="addressDetail" id="address_detail">
              <span class="error"><form:errors path="address"/></span>
            </div>

            <div class="CTA">
              <input type="submit" value="Signup Now">
              <a href="#" class="switch">로그인</a>
            </div>
          </form>
        </div><!-- End Signup Form -->
      </div>
    </div>

  </section>

  <!-- 에러 메시지 alert -->
  <div id="errorSection">
    <%@ include file="errorMsgAlert.jsp" %>
  </div>

  <script>
    var errorMsg = ${errorMsg};
    if (errorMsg) {
      alert(errorMsg);
    }
  </script>



</div>
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
<script  src="../../static/js/userscript.js"></script>

<%-- 다음 주소 api --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../../static/js/address-api.js"></script>


</body>
</html>

