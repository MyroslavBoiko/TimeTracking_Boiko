<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5"  onchange="getElementById('btnEnable').removeAttribute('disabled')">
            <option disabled><fmt:message key="deleteRequests" bundle="${locale}"/></option>

            <c:forEach items="${requestScope.userAssignments}" var="requestToDelete">
                <option>
                    <c:out value="${requestToDelete.activityDescription} :"/>
                    <c:out value="${requestToDelete.totalTime}"/>
                </option>
            </c:forEach>
        </select>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveRequestsToAdd&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="requestToDelete"/>
        <input id="btnEnable" type="submit" value="<fmt:message key="sendRequestToDelete" bundle="${locale}"/>" disabled/>
    </form>
</div>
