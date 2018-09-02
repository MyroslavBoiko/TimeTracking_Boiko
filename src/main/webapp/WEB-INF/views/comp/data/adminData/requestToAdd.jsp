<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5" onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="addRequests" bundle="${locale}"/></option>
            <c:forEach items="${requestScope.requestsToAdd}" var="requestToAdd">
                <option>
                    <c:out value="${requestToAdd.key}"/>
                    <c:out value="${requestToAdd.value}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveRequestsToAdd&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="createAssign">
        <input id="btnEnable" type="submit" value="<fmt:message key="createAssign" bundle="${locale}"/>" disabled>



    </form>
</div>
