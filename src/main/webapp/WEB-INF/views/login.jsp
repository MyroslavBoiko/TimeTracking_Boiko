<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 15.08.2018
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../../css/login.css" />
</head>
<body>
<div class="loginbox">
    <h1><fmt:message key="loginTitle" bundle="${locale}"/></h1>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login">
        <p><fmt:message key="email" bundle="${locale}"/></p>
        <input type="text" name="email" placeholder="<fmt:message key="enterEmail" bundle="${locale}"/>" required>
        <p><fmt:message key="password" bundle="${locale}"/></p>
        <input type="password" name="password" placeholder="<fmt:message key="enterPassword" bundle="${locale}"/>" required>
        <c:if test="${not empty requestScope.errorMessage}">
            <label class="error"><fmt:message key="${requestScope.errorMessage}" bundle="${messages}"/></label>
        </c:if>
        <input type="submit" name="" value="<fmt:message key="logIn" bundle="${locale}"/>">
        <a href="controller?command=registerPage"><fmt:message key="noAccount" bundle="${locale}"/></a>
    </form>
</div>
</body>
</html>