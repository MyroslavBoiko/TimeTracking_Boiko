<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>

<div class="topnav">
    <a href="controller?command=indexPage" class="active left btn" ><h1><fmt:message key="homePage" bundle="${locale}"/></h1></a>
    <%@include file="/WEB-INF/views/comp/languagePicker.jsp"%>
    <a href="controller?command=loginPage" class="right btn" style="margin-left: auto"><h1><fmt:message key="logIn" bundle="${locale}"/></h1></a>
    <a href="controller?command=registerPage" class="right btn"><h1><fmt:message key="signUp" bundle="${locale}"/></h1></a>
</div>