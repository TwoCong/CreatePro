<%@ page import="org.jsoup.Connection" %>
<%@ page import="java.util.ArrayDeque" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.Searcher" %>
<%@ page import="DAO.HighLightKey" %>
<%@ page import="DAO.Result" %>
<%--
  Created by IntelliJ IDEA.
  User: Two_Cong
  Date: 15/11/26
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <style type="text/css">
    .highlight{
      color: crimson;}
  </style>
</head>
<body>
  <%
    HighLightKey highLight = new HighLightKey();
    String keyword =request.getParameter("keyword");
    Result re = new Result();
    re.showCode(keyword);
    ArrayList contentL = new Result().showCode(keyword);
    contentL.size();
    %>
  <h1><%=keyword %></h1>
  <%


  %>

</body>
</html>
