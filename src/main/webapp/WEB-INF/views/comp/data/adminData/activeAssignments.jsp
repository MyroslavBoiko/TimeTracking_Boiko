<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="post">
        <select name="assignment" style="width:auto;" size="5" onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="assignments" bundle="${locale}"/></option>

            <c:forEach items="${requestScope.assignments}" var="assignment">
                <option>
                    <c:out value="${assignment.userEmail} :"/>
                    <c:out value="${assignment.activityDescription}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveAssignments&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="adminPage">
        <input id="btnEnable" type="submit" value="<fmt:message key="return" bundle="${locale}"/>">



    </form>
</div>
