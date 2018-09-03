<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 19.08.2018
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%-- JSTL core tag library. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- JSTL i18n tag library. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Custom tag  -->
<%@ taglib uri="/WEB-INF/usertag.tld" prefix="usertag"%>

<%-- Page directive. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%-- LocaleCommand lang setter --%>
<c:set var="language"
       value="${not empty lang ? lang : pageContext.request.locale.language}"
       scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle  basename="locale" var="locale"/>
<fmt:setBundle basename="messages" var="messages"/>
