<%@ page import="org.jsoup.Connection" %>
<%@ page import="java.util.ArrayDeque" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.Searcher" %>
<%@ page import="DAO.HighLightKey" %>
<%@ page import="DAO.Result" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.Iterator" %>
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
    String keyword = request.getParameter("keyword");
//    ArrayList al1 = (ArrayList)session.getAttribute("al");
   %> <%
    ArrayList alSearch = new Searcher().searchIndex(keyword);
    if (alSearch.size()>0){
      Iterator iter = alSearch.iterator();
      while(iter.hasNext()) {
        String contentlist = iter.next().toString();
    %>
  <p><%=contentlist%>
  </p>

  <%
      }
    }
    else
      {
        %>
        <a href="noFound.jsp">error</a>
      <%
      }
  %>

</body>
</html>
