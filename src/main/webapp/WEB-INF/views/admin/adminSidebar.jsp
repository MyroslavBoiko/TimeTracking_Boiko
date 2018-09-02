<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 19.08.2018
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<div class="container">
    <div class="left-side">
        <a href="controller?command=showUsers"><fmt:message key="clients" bundle="${locale}"/></a>
        <a href="controller?command=showActiveRequestsToAdd"><fmt:message key="addRequests" bundle="${locale}"/></a>
        <a href="controller?command=showActiveRequestsToDelete"><fmt:message key="deleteRequests" bundle="${locale}"/></a>
        <a href="controller?command=showActivities"><fmt:message key="activity" bundle="${locale}"/></a>
        <a href="controller?command=showActiveAssignments"><fmt:message key="assignment" bundle="${locale}"/></a>
    </div>
    <div class="right-side">

    </div>
</div>
