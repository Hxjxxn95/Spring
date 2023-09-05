<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: hojoonkim
  Date: 2023/08/30
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <thead>
    <tr>
      <th>id</th>
      <th>name</th>
      <th>age</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="item" items="${members}" >
      <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.age}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
