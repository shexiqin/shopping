<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:46
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
                        <p> [订单详细信息]</p>
                        <div style="width:500px;height:300px;margin:10px auto;">
                            <p>会员姓名： ${order.name}</p>
                            <p>订 单 号： ${order.ordercode}</p>
                            <p>收 件 人： ${order.username}</p>
                            <p>收件地址： ${order.address}</p>
                            <p>邮政编码： ${order.postcode}</p>
                            <p>收件电话： ${order.tel}</p>
                            <p>支付方式： ${order.pay}</p>
                            <p>运送方式： ${order.carry}</p>
                            <p>订单日期： ${order.orderdate}</p>
                            <p>备&nbsp;&nbsp;&nbsp;&nbsp;注： ${order.bz}</p>
                            <table width="400" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="50%" >商品名称</td>
                                    <td width="25%" >单&nbsp;&nbsp;价</td>
                                    <td width="25%" >数&nbsp;&nbsp;量</td>
                                </tr>
                                <c:forEach items="${orderDetailList}" var="detail">
                                    <tr>
                                        <td>${detail.goodsName}</td>
                                        <td>${detail.price}</td>
                                        <td>${detail.numbers}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <p class="ali_center">
                                <img src="images/29.gif" onclick="window.location.href='orderManageList.jsp'" style="border:0; cursor:hand"/>
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
