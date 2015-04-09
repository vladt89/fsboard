<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Message board</title>
  </head>
  <body>
      <table>
        <form action="ReceiveMessagesServlet" method="get">
          <tr>
            <td colspan="2"><b>Create new message</b></td>
          </tr>
          <tr>
            <td><label>Title:</label></td>
            <td><input pattern=".{3,15}" type="text" name="title"/></td>
          </tr>
          <tr>
            <td><label>Content:</label></td>
            <td><input pattern=".{3,15}" type="text" name="content"/></td>
          </tr>
          <tr>
            <td><label>Author:</label></td>
            <td><input pattern=".{3,15}" type="text" name="author"/></td>
          </tr>
          <tr>
            <td colspan="2"><u>Note:</u> text fields must have from 3 to 15 symbols</td>
          </tr>
          <tr>
            <td colspan="2" align="right"><input type="submit" name="command" value="create message"><br></td>
          </tr>
        </form>
        <form action="ReceiveMessagesServlet" method="get">
          <tr>
            <td colspan="2"><b>List all messages</b></td>
          </tr>
          <tr>
            <td>
            <label>Export type: </label>
            <select name="exportType">
              <option value="xml">xml</option>
              <option value="html">html</option>
            </select>
            </td>
            <td align="right"><input type="submit" name="command" value="list messages"></td>
          </tr>
        </form>
      </table>
  </body>
</html>
