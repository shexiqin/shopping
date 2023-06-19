<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/9
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <link href="styles/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="top">
    <p><a href="noticeManageList.jsp">公告管理</a> |<a href="goodsManageList.jsp">商品管理</a> | <a href="memberManageList.jsp">会员管理</a> | <a href="orderManageList.jsp">订单管理</a> | <a href="adminLogin.jsp">退出后台</a></p>
</div>
</body>
</html>
