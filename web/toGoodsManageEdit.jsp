<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/4/7
  Time: 10:47
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
    <title>电子商城</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-3.6.4.min.js"></script>
</head>
<script>
    $(function () {
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
                    <h3><img src="images/25.gif" class="flo_right"/><img src="images/23.gif" class="flo_left"/><span>商品列表</span>
                    </h3>
                    <div class="padd">
                        <p> [ 修改商品信息]</p>
                        <form namespace="/" id="goodsManageEdit" name="editForm"
                              action="GoodsController?doWhat=modifyGoods" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="shopGoods.id" value="9" id="goodsManageEdit_shopGoods_id"/>
                            <input type="hidden" name="shopGoods.picture" value="clothing.jpg"
                                   id="goodsManageEdit_shopGoods_picture"/>
                            <ul class="add">
                                <li>
                                    <span>  商品id</span>
                                    <input type="text" name="id" readonly value=${tbGoods.id}>
                                </li>
                                <li>
                                    <span> 商品名称： </span>
                                    <input type="text" name="goodsname"
                                           value=${tbGoods.goodsname} id="goodsManageEdit_shopGoods_goodsname"/>
                                    <font color="red">*</font>
                                </li>
                                <li style="height: 120px;">
                                    <span>商品图片</span>
                                    <img width="100px" height="100px" src="${tbGoods.picture}"/>
                                </li>
                                <li>
                                    <span>修改图片：</span>
                                    <input type="file" name="upload" value="" id="goodsManageEdit_upload"/>
                                </li>
                                <li>
                                    <span>价    格：</span>
                                    <input type="text" name="price" value=${tbGoods.price} id="goodsManageEdit_shopGoods_price"/>
                                    <font color="red">*</font>
                                </li>
                                <li style="height:100px;">
                                    <span>商品简介：</span>
                                    <textarea name="introduce" cols="60" rows="5" id="goodsManageEdit_shopGoods_introduce">${tbGoods.introduce}</textarea>
                                </li>

                            </ul>
                            <p class="ali_center">
                                <img src="images/12.gif" onclick="checkSumbit();" style="border:0; cursor:hand"/>
                                <img src="images/13.gif" onclick="document.editForm.reset();" style="border:0; cursor:hand"/>
                                <img src="images/29.gif" onclick="window.location.href='goodsManageList.jsp'" style="border:0; cursor:hand"/>
                            </p>
                        </form>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function checkSumbit() {
        var goodsname=$("input[name='goodsname']").val();
        var price=$("input[name='price']").val();
        if (goodsname==''||price==''){
            alert("商品名称或商品价格为空，请重新修改");
        }else {
            console.log(goodsname);
            $("form").submit();
        }
    }

</script>
</html>
