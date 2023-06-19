<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:12
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
<div class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>


    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right" /><img src="images/23.gif" class="flo_left" /><span>订单管理</span></h3>


                    <div class="padd">
                        <p> [公告详细信息]</p>
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
                                <img src="images/29.gif" onclick="window.location.href='noticeManageList.jsp'" style="border:0; cursor:hand"/>
                            </p>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
