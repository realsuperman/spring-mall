
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%--<!-- 에러 메시지 alert JSP -->--%>
<%--<%--%>
<%--//    String errorMsg = (String) request.getAttribute("errorMsg");--%>
<%--    if (request.getAttribute("errorMsg") != null) {--%>
<%--%>--%>
<%--<input type="hidden" id="errorMsg" value="<%= errorMsg %>">--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

<%--<!-- 에러 메시지 alert JS -->--%>
<%--<script>--%>
<%--    document.addEventListener("DOMContentLoaded", function() {--%>
<%--        var errorMsgInput = document.getElementById("errorMsg");--%>
<%--        if (errorMsgInput) {--%>
<%--            showAlert(errorMsgInput.value);--%>
<%--        }--%>
<%--    });--%>

<%--    function showAlert(message) {--%>
<%--        alert(message);--%>
<%--    }--%>
<%--</script>--%>

<%--&lt;%&ndash;<script>&ndash;%&gt;--%>
<%--&lt;%&ndash;    // 에러 메시지가 있다면 알림창에 표시&ndash;%&gt;--%>
<%--&lt;%&ndash;    <c:forEach items="${requestScope.keySet()}" var="key">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <c:if test="${key.startsWith('errorMsg_')}">&ndash;%&gt;--%>
<%--&lt;%&ndash;    var errorMessage = "${requestScope[key]}";&ndash;%&gt;--%>
<%--&lt;%&ndash;    alert(errorMessage);&ndash;%&gt;--%>
<%--&lt;%&ndash;    </c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;</script>&ndash;%&gt;--%>

<%--&lt;%&ndash;<script>&ndash;%&gt;--%>
<%--&lt;%&ndash;    // Spring에서 생성한 errorMsg 데이터를 받아옵니다.&ndash;%&gt;--%>
<%--&lt;%&ndash;    var errorMsgs = ${errorMsg};&ndash;%&gt;--%>

<%--&lt;%&ndash;    // errorMsgs가 배열 형태로 전달되었다면 각 메시지를 알림창으로 띄웁니다.&ndash;%&gt;--%>
<%--&lt;%&ndash;    if (Array.isArray(errorMsgs)) {&ndash;%&gt;--%>
<%--&lt;%&ndash;        for (var i = 0; i < errorMsgs.length; i++) {&ndash;%&gt;--%>
<%--&lt;%&ndash;            alert(errorMsgs[i].defaultMessage);&ndash;%&gt;--%>
<%--&lt;%&ndash;        }&ndash;%&gt;--%>
<%--&lt;%&ndash;    }&ndash;%&gt;--%>
<%--&lt;%&ndash;    // errorMsgs가 문자열 형태로 전달되었다면 그대로 알림창으로 띄웁니다.&ndash;%&gt;--%>
<%--&lt;%&ndash;    else if (typeof errorMsgs === 'string') {&ndash;%&gt;--%>
<%--&lt;%&ndash;        alert(errorMsgs);&ndash;%&gt;--%>
<%--&lt;%&ndash;    }&ndash;%&gt;--%>
<%--&lt;%&ndash;</script>&ndash;%&gt;--%>