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
        $("#submitImg").bind("click",function () {
            checkNull()
        });
    })
    function checkNull(){
        var title=$("input[name='title']").val();
        var endDate=$("input[name='endDate']").val();
        var creat=new Date().getTime()+86400000;
        var end=new Date(endDate).getTime();
        if (title==null||title==""){
            alert("标题不能为空");
        }else if (endDate==null||endDate==""){
            alert("结束时间不能为空");
        }else if (creat>end){
            alert("发布总时长最少为一天，请重新填写");
        }else {
            $("#myForm").submit();
        }
    }
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
                        <p>[添加公告信息]</p>
                        <form id="myForm" name="myForm" action="NoticeController?doWhat=addNotice" method="post" enctype="multipart/form-data">
                            <ul class="add">
                                <li>
                                    <span> 公告标题： </span>
                                    <input type="text" name="title"/>
                                    <font color="red">*</font>
                                </li>
                                <li>
                                    <span>截止时间：</span>
                                    <input type="text" name="endDate">
                                    <font color="red">* 日期格式:yyyy-MM-dd HH:mm:ss</font>
                                </li>
                                <li style="height: 80px">
                                    <span>公告内容：</span>
                                    <textarea name="content" cols="60" rows="5"></textarea>
                                </li>
                                <li style="height: 40px">
                                    <span>上传附件:</span><input type="file" name="picture" >
                                </li>
                                <li>
                                    <div style="line-height:30px;text-align: center;">
                                        <img src="images/12.gif" id="submitImg" style="border:0; cursor:hand;padding: 0 10px;"/>
                                        <img src="images/13.gif" onclick="document.myForm.reset()" style="border:0; cursor:hand; padding: 0 10px;"/>
                                        <img src="images/29.gif" onclick="window.location.href='noticeManageList.jsp'" style="border:0; cursor:hand; padding: 0 10px;"/>
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
</html>
