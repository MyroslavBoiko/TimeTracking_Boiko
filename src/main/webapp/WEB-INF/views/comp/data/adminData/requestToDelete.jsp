<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5" onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="requestToDeleteAssign" bundle="${locale}"/></option>

            <c:forEach items="${requestScope.requestsToDelete}" var="requestToDelete">
                <option>
                    <c:out value="${requestToDelete.key}"/>
                    <c:out value="${requestToDelete.value}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveRequestsToDelete&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="deleteAssign">
        <input id="btnEnable" type="submit" value="<fmt:message key="deleteAssign" bundle="${locale}"/>" disabled>
    </form>
</div>
