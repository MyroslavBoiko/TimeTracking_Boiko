
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 16.08.2018
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>
<html>
    <head>
        <title>Sign up</title>
        <link rel="stylesheet" href="../../css/login.css" type="text/css">
    </head>
    <body>
    <div class="loginbox">
        <h1><fmt:message key="createAcc" bundle="${locale}"/></h1>
        <form>
            <p><fmt:message key="email" bundle="${locale}"/></p>
            <input type="text" name="email" placeholder="<fmt:message key="enterEmail" bundle="${locale}"/>" required>
            <label class="error">${emailError}</label>
            <p><fmt:message key="password" bundle="${locale}"/></p>
            <input type="password" name="password" placeholder="<fmt:message key="enterPassword" bundle="${locale}"/>" required>
            <label class="error">${passwordError}</label>
            <p><fmt:message key="firstName" bundle="${locale}"/></p>
            <input type="text" name=first_name"" placeholder="<fmt:message key="enterFirstName" bundle="${locale}"/>" required>
            <label class="error">${firstNameError}</label>
            <p><fmt:message key="lastName" bundle="${locale}"/></p>
            <input type="text" name="last_name" placeholder="<fmt:message key="enterLastName" bundle="${locale}"/>" required>
            <label class="error">${lastNameError}</label>
            <input type="submit" value="<fmt:message key="signUp" bundle="${locale}"/>">
            <input type="hidden" name="command" value="registration">
            <a href="controller?command=loginPage"><fmt:message key="haveAcc" bundle="${locale}"/></a>
        </form>
    </div>
    </body>
</html>

