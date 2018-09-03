<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 03.09.2018
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp" %>
<div>
    <form action="controller" method="post">
        <table style="width:auto">
            <tr>
                <th><fmt:message key="description" bundle="${locale}"/></th>
            </tr>

            <c:forEach items="${activities}" var="activity">
                <tr>
                    <td><c:out value="${activity.description}"/></td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach var="i" begin="1" end="${pagesCount}">
            <a href="controller?command=showActivitiesAdmin&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <input type="hidden" name="command" value="adminPage">
        <input id="btnEnable" type="submit" value="<fmt:message key="return" bundle="${locale}"/>">
    </form>
</div>
