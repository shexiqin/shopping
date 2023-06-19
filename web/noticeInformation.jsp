<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    <div id="content">
        <div class="cartDet">
            <div class="order_bg">
                <h4>公告详细信息</h4>
                    <div style="width:500px;height:300px;margin:10px auto;">
                        <p>公告标题： ${notice.title}</p>
                        <p>公告内容： ${notice.content}</p>
                        <p>发布时间： ${notice.creatdate}</p>
                        <p>截止时间： ${notice.enddate}</p>
                        <p>
                            <span>附件：</span>
                            <img style="width: 200px;" src="${notice.addfile}">
                        </p>
                        <p class="ali_center">
                            <img src="images/29.gif" onclick="window.location.href='index.jsp'" style="border:0; cursor:hand"/>
                        </p>
                    </div>
            </div>
        </div>
    </div>
</body>
</html>
