<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<div>
    <form method="post" action="controller">
        <label for="desc"><fmt:message key="description" bundle="${locale}"/></label>
        <input id="desc" type="text" name="description" value="${description}" readonly>
        <label for="time"><fmt:message key="time" bundle="${locale}"/></label>
        <input type="text" name="time" id="time" required>
        <input type="hidden" name="command" value="saveTime">
        <input type="submit" value="<fmt:message key="save" bundle="${locale}"/>">
        <c:if test="${not empty errorInputNumber}">
            <p style="color: red"><fmt:message key="${errorInputNumber}" bundle="${messages}"/></p>
        </c:if>
    </form>
</div>
