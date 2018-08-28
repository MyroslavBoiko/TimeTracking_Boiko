<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 19.08.2018
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<c:choose>
    <c:when test="${type eq 'Admin'}">
        <%@include file="../headers/adminheader.jsp"%>
        <c:if test="${isIndex != true}">
            <%@include file="/WEB-INF/views/admin/adminSidebar.jsp"%>
        </c:if>
    </c:when>

    <c:when test="${type eq 'Client'}">
        <%@include file="../headers/clientheader.jsp"%>
        <c:if test="${isIndex != true}">
            <%@include file="/WEB-INF/views/client/clientSidebar.jsp"%>
        </c:if>
    </c:when>

    <c:otherwise>
        <%@include file="../headers/notregisteredheader.jsp"%>
    </c:otherwise>
</c:choose>


