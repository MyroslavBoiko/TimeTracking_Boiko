<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>

<div>
    <form action="controller" method="get">
        <select name="assignment" style="width:auto;" size="5">
            <option disabled>Assignments</option>

            <c:forEach items="${requestScope.userAssignments}" var="assignment">
                <option>
                    <c:out value="${assignment.activityDescription} :"/>
                    <c:out value="${assignment.totalTime}"/>
                </option>
            </c:forEach>
        </select>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showUserAssignments&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <br>
        <input type="hidden" name="command" value="setTime">
        <input type="submit" value="Set Time"/>
    </form>
</div>