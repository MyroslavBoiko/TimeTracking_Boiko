<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.08.2018
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>

<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <table style="width:auto">
        <tr>
            <th><fmt:message key="firstName" bundle="${locale}"/></th>
            <th><fmt:message key="lastName" bundle="${locale}"/></th>
        </tr>

        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
            </tr>
        </c:forEach>
    </table>
    <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
        <a href="controller?command=showUsers&page=${i}"><c:out value="${i}"/></a>
    </c:forEach>

</div>


