
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>


<div class="topnav">
    <c:choose>
        <c:when test="${isIndex}">
            <a href="controller?command=indexPage" class="active left btn" ><h1><fmt:message key="homePage" bundle="${locale}"/></h1></a>
            <a href="controller?command=adminPage" class="left btn">
                <h1>
                    <fmt:message key="profile" bundle="${locale}"/>
                    <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/>
                </h1>
            </a>
            <%@include file="/WEB-INF/views/comp/languagePicker.jsp"%>
        </c:when>
        <c:otherwise>
            <a href="controller?command=indexPage" class="left btn" ><h1><fmt:message key="homePage" bundle="${locale}"/></h1></a>
            <a href="controller?command=adminPage" class="active left btn">
                <h1>
                    <fmt:message key="profile" bundle="${locale}"/>
                    <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/>
                </h1>
            </a>
            <%@include file="/WEB-INF/views/comp/languagePicker.jsp"%>
        </c:otherwise>
    </c:choose>
    <a href="controller?command=logout" class="right btn" style="margin-left: auto"><h1><fmt:message key="logOut" bundle="${locale}"/></h1></a>
</div>
