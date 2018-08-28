<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>

<div>
    <form action="controller" method="get">
        <select name="description" style="width:auto;" multiple size="5">
            <option disabled>Choose activity</option>

            <c:forEach items="${requestScope.activities}" var="activity">
                <option><c:out value="${activity.description}"/></option>
            </c:forEach>
        </select>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActivities&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <input type="hidden" name="command" value="createAddRequest">
        <input type="submit" value="Add request">
    </form>
</div>
