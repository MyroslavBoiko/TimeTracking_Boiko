<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<div>
    <form action="controller" method="get">
        <select name="request" style="width:auto;" size="5">
            <option disabled>Delete requests</option>

            <c:forEach items="${requestScope.userAssignments}" var="requestToDelete">
                <option>
                    <c:out value="${requestToDelete.activityDescription} :"/>
                    <c:out value="${requestToDelete.totalTime}"/>
                </option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="requestToDelete"/>
        <input type="submit" value="Send request to delete"/>
        <br>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveRequestsToAdd&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>

    </form>
</div>
