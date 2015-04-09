<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Message board</title>
  </head>
  <body>
    <form action="ReceiveMessagesServlet" method="get">
      <table>
        <tr>
          <td colspan="2"><b>Create new message</b></td>
        </tr>
        <tr>
          <td><label>Title:</label></td>
          <td><input type="text" name="title"/></td>
        </tr>
        <tr>
          <td><label>Content:</label></td>
          <td><input type="text" name="content"/></td>
        </tr>
        <tr>
          <td><label>Author:</label></td>
          <td><input type="text" name="author"/></td>
        </tr>
        <tr>
          <td colspan="2" align="right"><input type="submit" name="command" value="create message"><br></td>
        </tr>
        <tr>
          <td colspan="2"><b>List all messages</b></td>
        </tr>
        <tr>
          <td>
            <label>Export type: </label>
            <select name="exportType">
              <option value="xml">xml</option>
              <option value="html">html</option>
              <option value="csv">csv</option>
            </select>
          </td>
          <td align="right"><input type="submit" name="command" value="list messages"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
