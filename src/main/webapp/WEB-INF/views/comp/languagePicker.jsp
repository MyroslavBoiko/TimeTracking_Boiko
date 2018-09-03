<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 02.09.2018
  Time: 01:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="initSettings.jsp"%>
<div class="picker">
    <form action="controller" method="get">
        <select class="langpick" name="localeLang" onchange="this.form.submit()">
            <c:if test="${language == 'ru'}">
                <option value="ru" selected><fmt:message key="russianLang" bundle="${locale}"/> </option>
                <option value="ua"><fmt:message key="ukrainianLang" bundle="${locale}"/></option>
                <option value="en"><fmt:message key="englishLang" bundle="${locale}"/></option>
            </c:if>
            <c:if test="${language == 'ua'}">
                <option value="ru"><fmt:message key="russianLang" bundle="${locale}"/></option>
                <option value="ua" selected><fmt:message key="ukrainianLang" bundle="${locale}"/></option>
                <option value="en"><fmt:message key="englishLang" bundle="${locale}"/></option>
            </c:if>
            <c:if test="${language == 'en'}">
                <option value="ru"><fmt:message key="russianLang" bundle="${locale}"/></option>
                <option value="ua"><fmt:message key="ukrainianLang" bundle="${locale}"/></option>
                <option value="en" selected><fmt:message key="englishLang" bundle="${locale}"/></option>
            </c:if>
        </select>
        <input type="hidden" name="command" value="locale">
        <input type="hidden" name="pageParam" value="${currentPage}">
    </form>
</div>
