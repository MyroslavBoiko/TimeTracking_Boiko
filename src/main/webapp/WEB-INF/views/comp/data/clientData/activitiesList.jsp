<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="get">
        <select name="description" style="width:auto;" size="5" onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="chooseActivity" bundle="${locale}"/></option>
            <c:forEach items="${activities}" var="activity">
                <option><c:out value="${activity.description}"/></option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${pagesCount}">
            <a href="controller?command=showActivities&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="createAddRequest">
        <input id="btnEnable" type="submit" value="<fmt:message key="requestToAdd" bundle="${locale}"/>" disabled>
        <br>
        <c:if test="${not empty errorMessage}">
            <p style="color: red"><fmt:message key="${errorMessage}" bundle="${messages}"/></p>
        </c:if>
    </form>
</div>
