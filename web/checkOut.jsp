<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:40
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
<c:if test="${msg != null}">
    ${msg}
</c:if>
<jsp:include page="top.jsp"></jsp:include>
<div id="container">
    <div id="content">
        <div class="userinfo" style="background:url(images/38.gif) no-repeat;">
            <form id="toCartConfirm" name="checkForm" onsubmit="return true;" action="OrderController?doWhat=addOrder" method="post">
                <ul>
                    <li>
                        <span>收 件 人：</span>
                        <input type="text" name="shopOrder.username" value="" id="toCartConfirm_shopOrder_username"/>
                        <font color="red"> *</font>
                    </li>
                    <li>
                        <span>邮寄地址：</span>
                        <input type="text" name="shopOrder.address" value="" id="toCartConfirm_shopOrder_address"/>
                        <font color="red"> *</font>
                    </li>
                    <li>
                        <span>邮政编码：</span>
                        <input type="text" name="shopOrder.postcode" maxlength="6" value="" id="toCartConfirm_shopOrder_postcode"/>
                        <font color="red"> *</font>
                    </li>
                    <li>
                        <span>联系电话：</span>
                        <input type="text" name="shopOrder.tel" maxlength="11" value="" id="toCartConfirm_shopOrder_tel"/>
                        <font color="red"> *</font>
                    </li>
                    <li>
                        <span>付款方式：</span>
                        <select id="pay" name="shopOrder.pay"></select>
                        <font color="red"> *</font>
                    </li>
                    <li>
                        <span>运送方式：</span>
                        <select id="carry" name="shopOrder.carry"></select>
                        <font color="red"> *</font>
                    </li>
                    <li style="height: 70px;">
                        <span>备    注：</span>
                        <textarea name="shopOrder.bz" cols="50" rows="5" id="toCartConfirm_shopOrder_bz"></textarea>
                        <font color="red"> *</font>
                    </li>
                </ul>
                <div class="button_img">
                    <img   src="images/12.gif"   onclick="document.checkForm.submit();" style= "CURSOR:hand"/>
                    <img   src="images/13.gif"   onclick="document.checkForm.reset();" style= "CURSOR:hand"/>
                    <img   src="images/29.gif"   onclick="window.location.href='#'" style= "CURSOR:hand"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        getPayAndCarry();

    });

    function getPayAndCarry() {
        $.ajax({
            type:'post',
            url:'TypeController',
            data:'doWhat=getPayAndCarry',
            dataType:'json',
            success:function (data) {
                let payList = data.payList;
                let carryList = data.carryList;
                $.each(payList,function (i,item) {
                    let option1 = "<option>"+item.name+"</option>";
                    $(option1).val(item.name);
                    $("#pay").append(option1);
                });
                $.each(carryList,function (i,item) {
                    let option2 = "<option>"+item.name+"</option>";
                    $(option2).val(item.name);
                    $("#carry").append(option2);
                });
            }
        });
    }
</script>
</html>
