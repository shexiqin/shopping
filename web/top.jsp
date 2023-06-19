<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/4/8
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <title>顶部导航栏</title>
    <link rel="stylesheet" href="styles/style.css">
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>

    <script>
        $(function () {
           shoppingCartCount();

        });


    </script>
</head>
<body>
<header>
    <div class="nav-bg">
        <a id="userId" hidden>${member.id}</a>
        <div class="container">
            <ul class="nav-top">
                <li style="float: left;">有 问 题 请 您 及 时 拨 打 24 小 时 服 务 热 线 4 0 0 8 0 0 6 0 0</li>
                <a href="adminLogin.jsp">后台管理入口</a>
                <li><a href="shoppingCart.jsp" id="sCount"></a></li>
                <li id="gundong">您  的 满 意 是 我 们 最 大 的 幸 福， 祝 你 购 物 愉 快!</li>
            </ul>
            <b>
                <c:if test="${member.grade=='铜牌会员'}">
                    <a > 会员等级 <img src="images/tongpai.png"/></a>
                </c:if>
                <c:if test="${member.grade=='银牌会员'}">
                    <a > 会员等级 <img src="images/yinpaihuiyuan.png"/></a>
                </c:if>
                <c:if test="${member.grade=='黄金会员'}">
                    <a > 会员等级 <img src="images/jinpaihuiyuan.png"/></a>
                </c:if>
                <c:if test="${member.grade=='白金会员'}">
                    <a > 会员等级 <img src="images/baijin.png"/></a>
                </c:if>
                <c:if test="${member.grade=='钻石会员'}">
                    <a > 会员等级 <img src="images/zuoshi.png"/></a>
                </c:if>

            </b>
        </div>
    </div>
    <div class="container">

        <marquee width="500px" style="float: left;">
            <c:if test="${member != null}">
            <p style="font-size:30px;color:orange; margin-top: 22px;">会员${member.username}欢迎登陆~~~</p>
            </c:if>
        </marquee>

    </div>
</header>

<div class="nav">
    <div class="container">
        <c:if test="${member != null}">
            <a href="index.jsp" >首页</a> |
            <a href="moreGoods.jsp">更多商品</a> |
            <a href="checkOrders.jsp">查看订单</a> |
            <a href="shoppingCart.jsp">购物车</a> |
            <a href="memberModify.jsp">会员资料修改</a> |
            <a href="MemberController?doWhat=logout">退出登录</a>
        </c:if>

        <c:if test="${member == null}">
            <a href="index.jsp" >首页</a> |
            <a href="moreGoods.jsp">更多商品</a> |
            <a href="shoppingCart.jsp">购物车</a> |
            <a href="login.jsp">会员登录</a>
        </c:if>
    </div>
</div>
</body>
<script type="text/javascript">
    function gundong(){
        var div=document.getElementById("gundong");
        var txt=div.innerHTML;

        var arr=txt.split("");//把字符串变成数组
        var first=arr.shift(); //删除数组里

        arr.push(first);
        txt=arr.join("");
        div.innerHTML=txt;

    }
    setInterval(gundong,300);

    function shoppingCartCount() {
        let userId=$("#userId").html();
        $.ajax({
            type:"post",
            url:"ShoppingCartController",
            data:"doWhat=getShoppingCartCount&memberId="+userId,
            success:function (data) {
                if(data>=1){
                    $("#sCount").text("购物车 "+data+" 件");
                }else {
                    data=0;
                    $("#sCount").text("购物车 "+data+" 件");
                }
            }
        });
    }
</script>
</html>
