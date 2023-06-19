<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:40
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

    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>

<form namespace="/" id="adminLogin" name="loginForm" action="AdminController?doWhat=adminLogin" method="post">
    <div class="login_bg">
        <p>
            用户名：<input type="text" name="manager"  size="9" id="adminLogin_shopManager_manager"/>
            密码：<input type="password" name="password" size="9" id="adminLogin_shopManager_password"/>
            <img id="denglu" src="images/19.gif" onclick="document.loginForm.submit()" style="border:0; cursor:hand"/>
            <img src="images/22.gif" onclick="window.location.href='index.jsp';" style="border:0; cursor:hand"/>
        </p>
        <div style="width:446px;height:30px;margin:30px auto;">
        </div>
    </div>
</form>





</body>
</html>
