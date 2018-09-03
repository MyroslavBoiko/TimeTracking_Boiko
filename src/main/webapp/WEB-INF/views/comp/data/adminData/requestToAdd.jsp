<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5">
            <option disabled>Add requests</option>
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
        <input type="submit" value="Create assign">



    </form>
</div>
