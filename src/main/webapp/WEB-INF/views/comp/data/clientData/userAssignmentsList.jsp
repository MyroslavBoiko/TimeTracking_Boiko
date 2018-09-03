<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="get">
        <select name="assignment" style="width:auto;" size="5" onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="assignments" bundle="${locale}"/></option>

            <c:forEach items="${requestScope.userAssignments}" var="assignment">
                <option>
                    <c:out value="${assignment.activityDescription} :"/>
                    <c:out value="${assignment.totalTime}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showMyAssignments&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="setTime">
        <input id="btnEnable" type="submit" value="<fmt:message key="setTime" bundle="${locale}"/>" disabled/>


    </form>
</div>