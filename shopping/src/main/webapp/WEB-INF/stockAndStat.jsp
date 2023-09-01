<%--
  Created by IntelliJ IDEA.
  User: 최성훈
  Date: 2023-08-17
  Time: 오후 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/code.jsp" %>
<%@include file="common/pageCommonScript.jsp" %>
<%
    String mode = (String) request.getAttribute("page");
%>

<script>
    var statMap = new Map();
    var currentPage = 0;

    $(document).ready(function() {
        initDesign('<%= mode %>');

        $('#add').html("상품 추가");
        $('#control').html("재고 관리");
        $('#stat').html("상품 상태 관리");

        $('#searchInput').on('keyup', function(event) {
            currentPage = 1;
            currentGroup = 0;
            if (event.keyCode === 13) {
                $("#dynamicDiv").empty();
                searchStock();
            }
        });

        $('#searchStockButton').on('click', function() {
            currentPage = 1;
            currentGroup = 0;
            $("#dynamicDiv").empty();
            searchStock();
        });

        $('#statConfirmButton').on('click',function (){
            let cargoStatusArray = [];

            Object.keys(statMap).forEach(function(cargoId) {
                let status = statMap[cargoId];
                cargoStatusArray.push({ cargoId: cargoId, status: status });
            });

            if (cargoStatusArray.length !== 0) {
                $.ajax({
                    url: "/stock/stat",
                    type: "POST",
                    async: false,
                    data: JSON.stringify(cargoStatusArray), // 배열을 JSON으로 변환하여 전송
                    contentType: "application/json", // JSON 타입 설정
                    success: function() {
                        alert("업데이트 성공!");
                    },
                    error: function(error) { // 실패한 경우 에러메시지 출력(예외 메시지)
                        alert(error.responseText);
                    }
                });
            }
        });
    });

    function initDesign(page){
        if(page==="stock"){
            $('#statConfirmButton').hide();
        }
        currentPage = 1;
        let htmlCode="";
        htmlCode = initHtmlCode(page,htmlCode);
        htmlCode+= callJsonList();
        htmlCode+="</table>";
        $('#dynamicDiv').append(htmlCode);
    }

    function searchStock(){
        let htmlCode="";
        htmlCode = initHtmlCode('<%= mode %>',htmlCode);
        htmlCode+=callJsonList();
        htmlCode+="</table>";
        $('#dynamicDiv').append(htmlCode);
    }

    function initHtmlCode(page,htmlCode){
        htmlCode = "<table><tr>";
        if(page === "stock"){ // 재고 관리
            htmlCode+="<th>제품 id</th><th>제품명</th><th>갯수</th>";
            htmlCode+="</tr>"
        }else{ // 상품 상태 관리
            htmlCode+="<th>Cargo id</th><th>제품명</th><th>상태</th>";
            htmlCode+="</tr>"
        }
        return htmlCode;
    }

    function callJsonList(){
        totalRow = 0;
        statMap = new Map();
        let data = '';

        let formData = {
            itemName : $('#searchInput').val(),
            page : currentPage-1,
            url : "/"+'<%= mode %>'
        };

        $.ajax({
            url:"/stock/list",
            type:"GET",
            async: false,
            data : formData,
            success: function(result) {
                data = parsingJson(result);
            }
        });

        displayPageNumbers();
        return data;
    }

    function parsingJson(inputString){
        let inputText = JSON.stringify(inputString);
        let size = parseInt(inputText.match(/"count":(\d+)/)[1]);
        if (size === 0) {
            alert("검색 결과가 존재하지 않아요");
            return '';
        }
        totalRow = size;

        return drawScreen(inputText);
    }

    function drawScreen(inputText){
        let htmlCode = "";
        let keyData = inputText.match(/"itemList":"\[.*\]"/)[0];
        let objectArrayString = keyData.match(/\[.*\]/)[0];
        let objectStrings;
        if('<%= mode %>' === 'stock'){
            objectStrings = objectArrayString.match(/StockDto\(.*?\)/g);
        }else{
            objectStrings = objectArrayString.match(/CargoDto\(.*?\)/g);
        }

        let extractedData = objectStrings.map(function(objectString) {
            if('<%= mode %>' === 'stock') {
                let itemId = objectString.match(/itemId=(\d+)/)[1];
                let itemName = objectString.match(/itemName=([^,]+)/)[1];
                let cnt = objectString.match(/cnt=(\d+)/)[1];
                htmlCode += "<tr>";
                htmlCode += "<td>";
                htmlCode += itemId;
                htmlCode += "</td>";
                htmlCode += "<td>";
                htmlCode += itemName;
                htmlCode += "</td>";
                htmlCode += "<td>";
                htmlCode += cnt;
                htmlCode += "</td>";
                htmlCode += "</tr>";
            }else{
                let cargoStat = getStatus('창고');
                let cargoId = objectString.match(/cargoId=(\d+)/)[1];
                let itemName = objectString.match(/itemName=([^,]+)/)[1];
                let statusId = objectString.match(/statusId=(\d+)/)[1];
                let selectOptions = '';
                cargoStat.forEach(function(option) {
                    let optionValue = option.value.split(';')[0];
                    let optionText = option.value.split(';')[1];
                    selectOptions += "<option value='" + optionValue + "'";
                    if (optionValue == statusId) {
                        selectOptions += " selected='selected'";
                    }
                    selectOptions += ">" + optionText + "</option>";
                });
                htmlCode += "<tr>";
                htmlCode += "<td>" + cargoId + "</td>";
                htmlCode += "<td>" + itemName + "</td>";
                htmlCode += "<td>";
                htmlCode += "<select onchange='updateStat(this, " + cargoId + ")'>";
                htmlCode += selectOptions;
                htmlCode += "</select>";
                htmlCode += "</td>";
                htmlCode += "</tr>";
            }
        });
        return htmlCode;
    }

    function moveAnotherPage(page){
        $("#dynamicDiv").empty();
        currentPage = page;
        searchStock();
    }

    function updateStat(selectElement, cargoId){
        statMap[cargoId] = selectElement.value;
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
        table {
            border-collapse: collapse; /* 테두리 겹침 해제 */
            width: 100%;
            border: 1px solid black; /* 전체 테이블 테두리 */
        }

        th, td {
            border: 1px solid black; /* 각각의 셀 테두리 */
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2; /* 헤더 배경색 */
        }
    </style>
</head>
<body class="sb-nav-fixed">

<%@include file="./common/header.jsp" %>
  
<div id="layoutSidenav">
    <%@include file="common/adminNav.html" %>
    <div id="layoutSidenav_content">
        <div class="center">
            <div style="text-align: center; margin-left: 200px; margin-right: 200px" id="search">
                <div style="display: flex;" id="searchField">
                    <input type="text" id="searchInput"/>
                    <button id="searchStockButton">검색</button>
                    <div style="margin-left: auto;">
                        <button id="statConfirmButton">변경</button>
                    </div>
                </div>
                <div style="margin-top: 20px;" id="dynamicDiv"></div>
            </div>
        </div>
    </div>
</div>

<div class="container d-flex justify-content-center">
    <div class="row">
        <div class="col">
            <ul class="pagination"></ul> <!--페이지네이션 버튼이 보일 곳-->
        </div>
    </div>
</div>

</body>
</html>