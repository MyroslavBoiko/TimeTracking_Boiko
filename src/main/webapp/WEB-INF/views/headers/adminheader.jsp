<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- JSTL i18n tag library. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language"
       value="${not empty lang ? lang : pageContext.request.locale.language}"
       scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>
<div class="topnav">
    <c:choose>
        <c:when test="${isIndex}">
            <a href="controller?command=indexPage" class="active left btn" ><h1>Home</h1></a>
            <a href="controller?command=adminPage" class="left btn"><h1>Profile <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/></h1></a>

            <div>
                <select name="lang">
                    <option value="ua">Ukrainian</option>
                    <option value="ru">Russian</option>
                    <option value="en">English</option>
                </select>
            </div>

        </c:when>
        <c:otherwise>
            <a href="controller?command=indexPage" class="left btn" ><h1><fmt:message key="homePage"/></h1></a>
            <a href="controller?command=adminPage" class="active left btn"><h1>Profile <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/></h1></a>

            <div>
                <form>
                    <select name="lang" onchange="this.form.submit()">
                        <option value="ru">Russian</option>
                        <option value="ua">Ukrainian</option>
                        <option value="en">English</option>
                    </select>
                    <input type="hidden" name="command" value="locale">
                </form>

            </div>
        </c:otherwise>
    </c:choose>

    <a href="controller?command=logout" class="right btn" style="margin-left: auto"><h1>Log out</h1></a>
</div>
