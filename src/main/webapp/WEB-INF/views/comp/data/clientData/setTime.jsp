<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 21.08.2018
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<div>
    <form method="post" action="controller">
        <label for="desc">Description</label>
        <input id="desc" type="text" name="description" value="${description}" readonly>
        <label for="time">Time</label>
        <input type="text" name="time" id="time" required>
        <input type="hidden" name="command" value="saveTime">
        <input type="submit" value="Save">
    </form>
</div>
