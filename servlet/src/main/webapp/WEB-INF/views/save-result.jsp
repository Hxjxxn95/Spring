<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: hojoonkim
  Date: 2023/08/30
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Member member = (Member) request.getAttribute("member"); %>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
  <ul>
    <li>id = <%= member.getId()%></li>
    <li>username = <%= member.getUsername()%>></li>
    <li>age = <%= member.getAge()%>></li>
  </ul>
  <a href="/index.html">메인</a> <br>

</body>
</html>
