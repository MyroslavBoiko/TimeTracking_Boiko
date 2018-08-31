<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5">
            <option disabled>Requests to delete assign</option>

            <c:forEach items="${requestScope.requestsToDelete}" var="requestToDelete">
                <option>
                    <c:out value="${requestToDelete.key}"/>
                    <c:out value="${requestToDelete.value}"/>
                </option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="deleteAssign">
        <input type="submit" value="Delete assign">
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveRequestsToDelete&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>

    </form>
</div>
