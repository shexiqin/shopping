<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();//
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>电子商城</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">
    <jsp:include page="top.jsp"></jsp:include>
    <div class="content">
        <h3 style="background: #ffc001">${goods.classname}</h3>
        <ul class="ul_list">
            <li>
                <img width="100" height="100" src="${goods.picture}">
                <span>${goods.goodsname}</span>
                <span>单价：${goods.price}</span>
                <span>商品简介:${goods.introduce}</span>
                <span><br/><img src="images/33.gif" alt="订购" onclick="window.location.href=' '" style="border:0; cursor:hand;"/>&nbsp;
         	<img src="images/29.gif" alt="返回" onclick="window.location.href='index.jsp'"  style="border:0; cursor:hand;"/>
         </span>
            </li>
        </ul>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
