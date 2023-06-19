<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 17:28
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
    <title>Title</title>
    <c:if test="${msg!=null}">
        ${msg}
    </c:if>
</head>
<link rel="stylesheet" type="text/css" href="styles/style.css"/>
<link rel="stylesheet" type="text/css" href="iconfont/iconfont.css"/>
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
<style type="text/css">
    .denglv marquee {
        height: 100px;
        line-height: 100px;
        font-size: 20px;
        color: orange;

    }

    .denglv {
        margin: 0 auto;
        width: 875px;
    }

    .denglv div {
        height: 369px;
        width: 388px;
        position: relative;
        left: 216px;
        top: 75px;
        background: rgba(255, 255, 255, 0.5);
        border-radius: 10px;
        padding: 50px 0 0 30px;
    }

    .denglv div ul {
        font-size: 15px;
        line-height: 60px;
        padding-left: 20px;
    }

    .denglv div ul input {
        width: 250px;
        height: 33px;
        color: rgb(200, 200, 200);
    }

    .denglv div ul i {
        display: inline-block;
        width: 50px;
        height: 38px;
        background: rgb(200, 200, 200);
        color: white;
        text-align: center;
        line-height: 34px;
    }

    .denglv div ul li:nth-child(5) input {
        width: 305px;
        background: #28b9ff;
        color: white;
        height: 35px;
    }

    .denglv div ul a {
        position: relative;
        right: -168px;
        bottom: -21px;
        color: black;
    }

    .denglv div ul a:hover {
        color: #28b9ff;
    }
</style>


<body>

<div class="denglv">
    <%--    <marquee>欢迎您!caoqing</marquee>--%>

    <div>
        <form action="MemberController?doWhat=reSetPassword" method="post">
            <ul>
                <li>密码重置</li>
                <li><i class="iconfont">&#xe6df;</i><input type="text" name="userName" id="userName"
                                                           placeholder="请输入要重置的会员名"/></li>
                <li><i class="iconfont">&#xe614;</i><input type="text" name="realName" id="realName"
                                                           placeholder="请输入真实姓名"/></li>
                <li><i class="iconfont">&#xe6df;</i><input type="text" name="tel" id="tel" placeholder="请输入手机号"/></li>
                <li><button id="resetPwd" onclick="checkNull()">重置密码</button></li>
            </ul>
        </form>
    </div>

    <script>
        function checkNull() {
            var username = $("#userName");
            var realname = $("#realName");
            var tel = $("#tel");
            if (username.val() == null || username.val() == "") {
                username.css("background", "pink");
                alert("用户名不能为空");
            } else if (realname.val() == null || realname.val() == "") {
                realname.css("background", "pink");
                alert("真实姓名不能为空");
            } else if (tel.val() == null || tel.val() == "") {
                tel.css("background", "pink");
                alert("手机号不能为空");
            } else {
                $("#resetPwd").submit();
            }
        }

    </script>
</div>

</body>
</html>
