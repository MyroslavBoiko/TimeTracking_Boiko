<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 02.09.2018
  Time: 01:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="initSettings.jsp"%>
<div class="picker">
    <form action="controller" method="get">
        <select class="langpick" name="localeLang" onchange="this.form.submit()">
            <c:if test="${language == 'ru'}">
                <option value="ru" selected>Russian</option>
                <option value="ua">Ukrainian</option>
                <option value="en">English</option>
            </c:if>
            <c:if test="${language == 'ua'}">
                <option value="ru">Russian</option>
                <option value="ua" selected>Ukrainian</option>
                <option value="en">English</option>
            </c:if>
            <c:if test="${language == 'en'}">
                <option value="ru">Russian</option>
                <option value="ua">Ukrainian</option>
                <option value="en" selected>English</option>
            </c:if>
        </select>
        <input type="hidden" name="command" value="locale">
        <input type="hidden" name="pageParam" value="${currentPage}">
    </form>
</div>
