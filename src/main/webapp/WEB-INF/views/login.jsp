<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 15.08.2018
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../../css/register.css" />
</head>
<body>
<div class="login-wrap flex">
    <div class="login-html">
        <label class="tab">Sign In</label>
        <div class="login-form">
            <div class="sign-in-htm">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="login">
                <div class="group">
                    <label for="user" class="label">Email</label>
                    <input id="user" type="email" name="email" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" name="password" class="input" data-type="password" required>
                </div>
                <%--<div class="group">--%>
                    <%--<input id="check" type="checkbox" class="check" checked>--%>
                    <%--<label for="check"><span class="icon"></span> Keep me Signed in</label>--%>
                <%--</div>--%>

                    <div class="group">
                        <input type="submit" class="button" value="Log In">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>