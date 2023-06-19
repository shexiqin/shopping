<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:47
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
    <base href="<%=basePath%>">
    <title>Title</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
</head>
<script>
    $(function () {
        showGoodsClass();
        $("#submitImg").bind("click",function () {
            $("#myForm").submit();
        });
    })
</script>
<body>
<c:if test="${msg != null}">
    ${msg}
</c:if>
<div class="main">
    <jsp:include page="managerTop.jsp"></jsp:include>
    <div class="adm_con">
        <div class="bg_3">
            <div class="bg_1">
                <div class="bg_2">
                    <h3><img src="images/25.gif" class="flo_right" /><img src="images/23.gif" class="flo_left" /><span>商品列表</span></h3>
                    <div class="padd">
                        <p>[添加商品信息]</p>
                        <form id="myForm" name="myForm" action="GoodsController?doWhat=addGoods" method="post" enctype="multipart/form-data">
                            <ul class="add">
                                <li>
                                    <span> 商品名称： </span>
                                    <input type="text" name="goodsname"/>
                                    <font color="red">*</font>
                                </li>
                                <li>
                                    <span>价&nbsp;&nbsp;&nbsp;&nbsp;格：</span>
                                    <input type="text" name="price" />
                                    <font color="red">*</font>
                                </li>
                                <li style="height: 80px">
                                    <span>商品简介：</span>
                                    <textarea name="introduce" cols="60" rows="5"></textarea>
                                </li>
                                <li>
                                    <span>商品类型:</span>
                                    <select id="classify" name="classify"></select>
                                </li>
                                <li style="height: 40px">
                                    <span>上传图片:</span><input type="file" name="picture">
                                </li>
                                <li>
                                    <div style="line-height:30px;text-align: center">
                                        <img src="images/12.gif" id="submitImg" style="border:0; cursor:hand"/>
                                        <img src="images/13.gif" onclick="document.myForm.reset()" style="border:0; cursor:hand"/>
                                        <img src="images/29.gif" onclick="window.location.href='goodsManageList.jsp'" style="border:0; cursor:hand"/>
                                    </div>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function showGoodsClass() {
        $.ajax({
            type: "POST",// 提交类型
            url: "GoodsController",// 提交地址
            data: "doWhat=showClass",// 提交时传递的参数
            dataType: "json",// 返回的类型 默认text
            async: true,// 默认异步 true   同步false
            cache: false,// 缓存
            success(data) {
                var tbClassesList = data.tbClassesList;
                $.each(tbClassesList, function (i, cls) {
                    let option  = "<option value='"+cls.name+"'>"+cls.name+"</option>";
                    $("#classify").append($(option));
                });
            }
        });
    }
</script>
</html>
