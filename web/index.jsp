<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Message board</title>
  </head>
  <body>
    <form action="ReceiveMessagesServlet" method="get">
      <table>
        <tr>
          <td><label>Title</label></td>
          <td><input type="text" name="title"/></td>
        </tr>
        <tr>
          <td><label>Content</label></td>
          <td><input type="text" name="content"/></td>
        </tr>
        <tr>
          <td><label>Author</label></td>
          <td><input type="text" name="author"/></td>
        </tr>
        <tr><td><input type="submit" name="command" value="create message"></td></tr>
        <tr><td><input type="submit" name="command" value="list messages"></td></tr>
      </table>
    </form>
  </body>
</html>
