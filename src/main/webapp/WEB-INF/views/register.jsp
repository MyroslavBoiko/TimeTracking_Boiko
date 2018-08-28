<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 16.08.2018
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="../../css/register.css" type="text/css">
    </head>
    <body>
        <div class="login-wrap flex">
             <div class="login-html">
                 <label class="tab">Sign Up</label>
                    <div class=login-form flex>
                         <div class="sign-up-htm">
                             <form action="controller" method="post">
                                 <div class="group">
                                     <label for="email" class="label">Email</label>
                                     <input id="email" type="email" name="email" class="input" required>
                                </div>
                                 <div class="group">
                                      <label for="pass" class="label">Password</label>
                                        <input id="pass" type="password" name="password" class="input" data-type="password" required>
                                 </div>
                                  <%--<div class="group">--%>
                                      <%--<label for="repass" class="label">Repeat--%>
                                         <%--Password</label>--%>
                                         <%--<input id="repass" type="password" class="input" data-type="password" required>--%>
                                  <%--</div>--%>
                                    <div class="group">
                                        <label for="first_name" class="label">First name</label>
                                        <input id="first_name" type="text" name="first_name" class="input" required>
                                    </div>
                                    <div class="group">
                                        <label for="last_name" class="label">Last name</label>
                                        <input id="last_name" type="text" name="last_name" class="input" required>
                                    </div>
                                     <div class="group">
                                        <input type="hidden" name="command" value="registration">
                                        <input type="submit" class="button" value="Sign Up">
                                     </div>
                             </form>
                         </div>
                    </div>
             </div>
        </div>
    </body>
</html>

