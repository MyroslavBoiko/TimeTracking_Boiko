<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 20.08.2018
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>


<div class="topnav">
    <c:choose>
        <c:when test="${isIndex}">
            <a href="controller?command=indexPage" class="active left btn"><h1>Home</h1></a>
            <a href="controller?command=clientPage" class="left btn"><h1>Profile <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/></h1></a>
            <div>
                <select name="lang">
                    <option value="ru">Ukrainian</option>
                    <option value="ru">Russian</option>
                    <option value="en">English</option>
                </select>
            </div>
        </c:when>
        <c:otherwise>
            <a href="controller?command=indexPage" class="left btn"><h1>Home</h1></a>
            <a href="controller?command=clientPage" class="active left btn"><h1>Profile <usertag:myTag firstname="${user.firstName}" lastname="${user.lastName}"/></h1></a>
            <div>
                <select name="lang">
                    <option value="ru">Ukrainian</option>
                    <option value="ru">Russian</option>
                    <option value="en">English</option>
                </select>
            </div>
        </c:otherwise>
    </c:choose>
    <a href="controller?command=logout" class="right btn" style="margin-left: auto"><h1>Log out</h1></a>
</div>
