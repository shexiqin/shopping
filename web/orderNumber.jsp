<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:07
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
    <link href="inc/style.css" rel="stylesheet" type="text/css" />
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="container">


    <jsp:include page="top.jsp"></jsp:include>


    <div id="content">
        <div class="userinfo" style="background:url(images/38.gif) no-repeat;">
            <ul>
                <li>
                    您的订单号：<font style="color:red;">${orderCode}</font>
                </li>
            </ul>
            <div class="button_img">
                <center>
                    <a href="index.jsp">继续购物</a>
                </center>

            </div>
        </div>
    </div>
</div>
</body>
</html>
