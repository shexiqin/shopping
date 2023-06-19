<%@ page import="bean.TbMember" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:42
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
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
</head>
<body>
<div class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>
    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right" /><img src="images/23.gif" class="flo_left" /><span>会员管理</span></h3>
                    <div class="padd">
                        <div style="width:500px;height:300px;margin:10px auto;">
                            <p>会 员 名： ${member.username}</p>
                            <p>真实姓名： ${member.truename}</p>
                            <p>密&nbsp;&nbsp;&nbsp;&nbsp;码： ${member.password}</p>
                            <p>城&nbsp;&nbsp;&nbsp;&nbsp;市： ${member.city}</p>
                            <p>地&nbsp;&nbsp;&nbsp;&nbsp;址： ${member.address}</p>
                            <p>邮政编码： ${member.postcode}</p>
                            <p>证件类型： ${member.cardtype}</p>
                            <p>证件号码： ${member.cardno}</p>
                            <p>电&nbsp;&nbsp;&nbsp;&nbsp;话： ${member.tel}</p>
                            <p>E-mail： ${member.email}</p>
                            <p>会员等级： ${member.grade}</p>
                            <p class="ali_center">
                                <img src="images/29.gif" onclick="window.location.href='memberManageList.jsp'" style="border:0; cursor:hand"/>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>
