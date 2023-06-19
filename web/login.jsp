<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:06
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
</head>
<script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles/style.css"/>
<link rel="stylesheet" type="text/css" href="iconfont/iconfont.css"/>
<style type="text/css">
    .denglv  marquee{
        height: 100px;
        line-height: 100px;
        font-size:20px;
        color:orange;

    }
    .denglv{
        margin: 0 auto;
        width: 875px;
    }

    .denglv  div{
        height: 369px;
        width:388px;
        position: relative;
        left: 216px;
        top: 75px;
        background: rgba(255,255,255,0.5);
        border-radius: 10px;
        padding: 50px 0 0 30px;
    }

    .denglv div ul{
        font-size: 15px;
        line-height:60px;
        padding-left:20px ;
    }

    .denglv div ul input{
        width: 250px;
        height: 33px;
        color: rgb(200,200,200);
    }
    .denglv  div ul i{
        display: inline-block;
        width: 50px;
        height: 38px;
        background:rgb(200,200,200);
        color: white;
        text-align: center;
        line-height: 34px;
    }

    .denglv div ul li:nth-child(4) input{
        width: 305px;
        background: #28b9ff;
        color: white;
        height: 35px;
    }

    .denglv  div ul a{
        position: relative;
        right: -168px;
        bottom: -21px;
        color: black;
    }
    .denglv div ul a:hover{
        color: #28b9ff;
    }
</style>
<c:if test="${msg!=null}">
    ${msg}
</c:if>
<body>
<div  class="denglv">
<%--    <marquee>欢迎您!caoqing</marquee>--%>
    <div>
        <form action="MemberController?doWhat=login" method="post">
          <ul>
            <li>会员登录</li>
            <li><i class="iconfont">&#xe6df;</i><input type="text" name="userName" placeholder="请输入会员名"/></li>
            <li><i class="iconfont">&#xe614;</i><input type="password" name="passWord" placeholder="请输入密码"/></li>
            <li><input type="submit" name="" id="" value="登&nbsp;&nbsp;录" /></li>
            <li><a href="resetPassword.jsp">忘记密码</a>&nbsp;<a href="register.jsp">免费注册</a></li>
         </ul>
        </form>
    </div>
</div>

</body>
</html>
