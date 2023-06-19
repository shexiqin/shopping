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
                        <p> [商品详细信息]</p>
                        <div style="width:500px;height:300px;margin:10px auto;">
                            <p>商品名称： ${goods.goodsname}</p>
                            <p>价&nbsp;&nbsp;&nbsp;&nbsp;格： ${goods.price}</p>
                            <p>
                                <span>图片：</span>
                                <img style="width: 100px;height: 100px;" src="${goods.picture}" title="商品图片">
                            </p>
                            <p>发布日期： ${goods.credate}</p>
                            <p>商品类别： ${goods.classname}</p>
                            <p>商品简介： ${goods.introduce}</p>
                            <p class="ali_center">
                                <img src="images/29.gif" onclick="window.location.href='goodsManageList.jsp'" style="border:0; cursor:hand"/>
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
