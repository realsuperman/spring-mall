<%--
  Created by IntelliJ IDEA.
  User: 최성훈
  Date: 2023-08-17
  Time: 오후 9:40
  To change this template use File | Setti<%@include file="./commonScript.jsp" %>ngs | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/code.jsp" %>
<%@include file="common/uploadPath.jsp" %>

<script>
    $(document).ready(function() {
        $('#add').html("상품 추가");
        $('#control').html("재고 관리");
        $('#stat').html("상품 상태 관리");
        initCategory();

        for (let i = 1; i <= 6; i++) {
            $("#fileInput" + i).on("change", function() {
                let selectedFile = this.files[0];

                if (selectedFile) {
                    let formData = new FormData();
                    formData.append("file", selectedFile);

                    $.ajax({
                        type: "POST",
                        url: "upload", // 서블릿 URL
                        data: formData,
                        async: false,
                        contentType: false,
                        processData: false,
                        success: function(response) {
                            $("#image" + i).attr("src", downPrefix + response + downSuffix);
                            $("#image" + i + "-name").val(response);
                        },
                    });
                }
            });
        }

        $("#largeCategory").on("change", function() { // 대분류 변경 이벤트
            clearCategory($("#detailCategory"));
            if($('#largeCategory').val()==''){
                clearCategory($('#middleCategory'));
                return;
            }

            const selectedOption = $("#largeCategory option:selected");
            const selectedValue = selectedOption.val();
            const selectedContent = selectedOption.text();
            const combinedValue = selectedValue + ";" + selectedContent;
            initializeCategorySelect($("#middleCategory"), getCategories(combinedValue), false);
        });

        $("#middleCategory").on("change", function() { // 중분류 변경 이벤트
            if($('#middleCategory').val()==''){
                clearCategory($('#detailCategory'));
                return;
            }

            const selectedOption = $("#middleCategory option:selected");
            const selectedValue = selectedOption.val();
            const selectedContent = selectedOption.text();
            const combinedValue = selectedValue + ";" + selectedContent;
            initializeCategorySelect($("#detailCategory"), getCategories(combinedValue), false);
        });

        $("#itemForm").submit(function(event) {
            if(!checkForm()){ // 프론트 체크
                event.preventDefault();
                return;
            }

            if(!validation()){ // 백엔드 체크
                event.preventDefault();
            }
        })

        window.onpageshow = function(event) { // 뒤로가기 누르면 모든 item 제거
            if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
                clearForm(); // 폼의 값 초기화
            }
        }
    });

    function initCategory(){
        initializeCategorySelect($("#largeCategory"), getCategories(null), true);
    }

    function initializeCategorySelect(selectElement, data, isKey) {
        selectElement.empty(); // 자식 옵션 제거
        selectElement.append('<option value="">선택</option>'); // "선택" 옵션 추가

        $.each(data, function(index, item) {
            let keyValue;
            if (isKey) {
                keyValue = item.key.split(';');
            } else {
                keyValue = item.value.split(';');
            }
            const value = keyValue[0];
            const content = keyValue[1];
            const optionHtml = '<option value="' + value + '">' + content + '</option>';
            selectElement.append(optionHtml);
        });

        selectElement.val(""); // 선택 상태 초기화
    }

    function clearCategory(selectElement) {
        selectElement.empty(); // 자식 옵션 제거
        selectElement.append('<option value="">선택</option>'); // "선택" 옵션 추가
        selectElement.val(""); // 선택 상태로 초기화
    }

    function checkForm(){
        let itemName = $('#item_name').val();
        let itemPrice = $('#item_price').val();
        let itemQuantity = $('#item_quantity').val();
        let itemDesc = $('#item_desc').val();
        let detailCategory = $('#detailCategory').val();

        // itemName은 비어있으면 안됨
        if (itemName.trim() === "") {
            alert("상품 이름을 입력해주세요.");
            return false;
        }

        // itemDesc는 비어있으면 안됨
        if (itemDesc.trim() === "") {
            alert("상품 설명을 입력해주세요.");
            return false;
        }

        // itemPrice는 숫자이면서 0~1000000 범위인지 체크
        if (!/^\d+$/.test(itemPrice) || itemPrice < 0 || itemPrice > 1000000) {
            alert("상품 가격은 숫자이며 0에서 1,000,000 사이여야 합니다.");
            return false;
        }

        // itemQuantity는 숫자이면서 0~100 범위인지 체크
        if (!/^\d+$/.test(itemQuantity) || itemQuantity < 0 || itemQuantity > 100) {
            alert("상품 수량은 숫자이며 0에서 100 사이여야 합니다.");
            return false;
        }

        // itemDesc는 최대 512자리인지 체크
        if (itemDesc.length > 512) {
            alert("상품 설명은 최대 512자까지 입력 가능합니다.");
            return false;
        }

        for (let i = 1; i <= 6; i++) {
            let image = $('#image' + i +"-name").val();
            if (image == "") {
                if(i==1){
                    alert("섬네일을 필수 입니다.")
                }else {
                    alert("이미지 #" + (i-1) + "을 입력해주세요.");
                }
                return false;
            }
        }

        if (detailCategory.trim() === "") {
            alert("상세 카테고리를 선택해주세요.");
            return false;
        }


        return true; // 모든 조건을 만족할 경우 true 반환
    }

    function validation(){
        let formData = {
            itemName : $('#item_name').val(),
            itemPrice: $('#item_price').val(),
            itemQuantity: $('#item_quantity').val(),
            itemDesc : $('#item_desc').val(),
            image1Name : $('#image1-name').val(),
            image2Name : $('#image2-name').val(),
            image3Name : $('#image3-name').val(),
            image4Name : $('#image4-name').val(),
            image5Name : $('#image5-name').val(),
            image6Name : $('#image6-name').val(),
            category : $('#detailCategory').val()
        };
        let returnValue;

        $.ajax({
            url: "/item-validation",
            type: "POST",
            data: formData,
            async: false,
            success: function() {
                returnValue = true;
            },error: function(jqXHR) {
                alert(jqXHR.responseText);
                returnValue = false;
            }
        });
        return returnValue;
    }

    function clearForm(){
        $('#item_name').val("");
        $('#item_price').val("");
        $('#item_quantity').val("");
        $('#item_desc').val("");
        $('#detailCategory').val("");
        for (let i = 1; i <= 6; i++) {
            $('#image' + i +"-name").val("");
            $('#fileInput'+i).val("");
        }
        initCategory();
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="../static/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="../static/assets/demo/chart-area-demo.js"></script>
<script src="../static/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="../static/js/datatables-simple-demo.js"></script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="../static/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .rounded-input {
            border-radius: 10px; /* 모서리 둥글기 정도 */
            padding: 5px;
            margin: 5px;
            border: 1px solid #ccc;
            width: 500px;
        }

        .rounded-textArea{
            border-radius: 10px; /* 모서리 둥글기 정도 */
            padding: 5px;
            margin: 5px;
            border: 1px solid #ccc;
            width: 500px;
            height: 150px;
        }

        img {
            border: 2px solid red; /* 2px 두께의 붉은색 테두리 설정 */
        }

        input[type="file"] {
            font-size: 1px; /* 파일명을 보이지 않게 함 */
        }
    </style>
</head>
<body class="sb-nav-fixed">
<%@include file="./common/header.jsp" %>
<div id="layoutSidenav">
    <%@include file="common/adminNav.html" %>
    <div id="layoutSidenav_content">
        <div class="center">
            <form id="itemForm" action="/item" method="POST">
                <b>제품명</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="rounded-input" type="text" name="item_name" id="item_name" placeholder="제품명을 입력하세요." maxlength="127"><br><br>
                <b>가격</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="rounded-input" type="number" name="item_price" id="item_price" placeholder="가격을 입력하세요" min="0" max="1000000"><br><br>
                <b>제품 갯수</b>&nbsp;
                <input class="rounded-input" type="number" name="item_quantity" id="item_quantity" placeholder="제품 갯수를 입력하세요" min="0" max="100"><br><br>

                <span style="float:left; margin-top:5px;"><b>제품 설명</b></span>&nbsp;
                <textarea class="rounded-textArea" name="item_desc" id="item_desc" class="rounded-textarea" placeholder="제품 상세를 입력하세요" maxlength="512"></textarea><br><br>


                <span style="float: left"><b>썸네일</b></span>
                <input type="text" style="display: none" id="image1-name" name="image1-name"><img style="width:500px; height: 200px; margin-left: 30px;" id="image1"><br><br>
                <input style="margin-left: 250px" type="file" id="fileInput1">

                <br><br>
                <div style="display: flex; flex-direction: row;">
                    <div style="width: 100px;">
                        <span><b>사진1</b></span>
                        <input type="text" style="display: none" id="image2-name" name="image2-name"><img style="width:100px; height: 100px;" id="image2"><br><br>
                        <input type="file" id="fileInput2">
                    </div>

                    <div style="width: 100px; margin-left: 20px;">
                        <span><b>사진2</b></span>
                        <input type="text" style="display: none" id="image3-name" name="image3-name"><img style="width:100px; height: 100px;" id="image3"><br><br>
                        <input type="file" id="fileInput3">
                    </div>

                    <div style="width: 100px; margin-left: 20px;">
                        <span><b>사진3</b></span>
                        <input type="text" style="display: none" id="image4-name" name="image4-name"><img style="width:100px; height: 100px;" id="image4"><br><br>
                        <input type="file" id="fileInput4">
                    </div>

                    <div style="width: 100px; margin-left: 20px;">
                        <span><b>사진4</b></span>
                        <input type="text" style="display: none" id="image5-name" name="image5-name"><img style="width:100px; height: 100px;" id="image5"><br><br>
                        <input type="file" id="fileInput5">
                    </div>

                    <div style="width: 100px; margin-left: 20px;">
                        <span><b>사진5</b></span>
                        <input type="text" style="display: none" id="image6-name" name="image6-name"><img style="width:100px; height: 100px;" id="image6"><br><br>
                        <input type="file" id="fileInput6">
                    </div>
                </div>

                <br><br>

                <div>
                    <select style="width: 185px" name="largeCategory" id="largeCategory">
                    </select>
                    <select style="width: 185px" name="middleCategory" id="middleCategory">
                        <option value="">선택</option>
                    </select>
                    <select style="width: 185px" name="detailCategory" id="detailCategory">
                        <option value="">선택</option>
                    </select>
                </div>
                <div style="text-align:center; margin-top: 20px;">
                    <button id="createItem" type="submit">상품 등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>