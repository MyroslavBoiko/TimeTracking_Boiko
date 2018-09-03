<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 10.08.2018
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/comp/initSettings.jsp"%>

<html>
<head>
    <title><fmt:message key="homePage" bundle="${locale}"/> </title>
    <link rel="stylesheet"  type="text/css" href="css/main.css">
</head>
  <body>
  <c:set var = "isIndex" value = "${true}"/>
    <div class="flex-container">
        <%@include file="/WEB-INF/views/comp/navbar.jsp"%>
    </div>
  </body>
</html>
